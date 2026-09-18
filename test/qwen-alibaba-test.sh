#!/usr/bin/env bash
set -euo pipefail

script_directory=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
repository_directory=$(CDPATH= cd -- "$script_directory/.." && pwd)
qwen_alibaba="$repository_directory/bin/qwen_alibaba"
temporary_directory=$(mktemp -d)
trap 'rm -rf "$temporary_directory"' EXIT

fake_curl="$temporary_directory/curl"
request_record="$temporary_directory/request"

cat > "$fake_curl" <<'FAKE_CURL'
#!/bin/sh
printf '%s\n' "$@" > "$QWEN_TEST_REQUEST_RECORD"
printf '%s\n' '{"id":"chatcmpl-test","model":"qwen3-coder-next","choices":[{"index":0,"message":{"role":"assistant","content":"patched"},"finish_reason":"stop"}],"usage":{"prompt_tokens":11,"completion_tokens":1,"total_tokens":12}}'
FAKE_CURL
chmod +x "$fake_curl"

export DASHSCOPE_API_KEY='test-secret'
export QWEN_CURL="$fake_curl"
export QWEN_TEST_REQUEST_RECORD="$request_record"
export QWEN_SECRET_FILE="$temporary_directory/no-secret-file"

chat_output=$(bash "$qwen_alibaba" chat 'inspect this repository')
test "$chat_output" = 'patched'

grep -Fx 'https://dashscope-intl.aliyuncs.com/compatible-mode/v1/chat/completions'   "$request_record" >/dev/null
grep -Fx 'Authorization: Bearer test-secret' "$request_record" >/dev/null

request_body=$(awk 'previous == "--data" { print; exit } { previous = $0 }' "$request_record")
test "$(printf '%s' "$request_body" | jq -r '.model')" = 'qwen3-coder-next'
test "$(printf '%s' "$request_body" | jq -r '.messages[1].content')" = 'inspect this repository'
test "$(printf '%s' "$request_body" | jq -r '.stream')" = 'false'

raw_response=$(printf '%s\n' '{"messages":[{"role":"user","content":"raw request"}]}' |
  bash "$qwen_alibaba" request -)
test "$(printf '%s' "$raw_response" | jq -r '.choices[0].message.content')" = 'patched'

request_body=$(awk 'previous == "--data" { print; exit } { previous = $0 }' "$request_record")
test "$(printf '%s' "$request_body" | jq -r '.model')" = 'qwen3-coder-next'
test "$(printf '%s' "$request_body" | jq -r '.messages[0].content')" = 'raw request'

rm -f "$request_record"
if printf '%s\n' '{"model":"qwen3-coder-30b-a3b-instruct","messages":[]}' |
    bash "$qwen_alibaba" request -     >"$temporary_directory/wrong-model.out" 2>"$temporary_directory/wrong-model.err"; then
  printf 'qwen_alibaba test: wrong model unexpectedly succeeded\n' >&2
  exit 1
fi
test ! -e "$request_record"
grep -F 'request must be valid JSON and use model qwen3-coder-next'   "$temporary_directory/wrong-model.err" >/dev/null

rm -f "$request_record"
if DASHSCOPE_API_KEY= bash "$qwen_alibaba" chat test     >"$temporary_directory/no-key.out" 2>"$temporary_directory/no-key.err"; then
  printf 'qwen_alibaba test: missing API key unexpectedly succeeded\n' >&2
  exit 1
fi
test ! -e "$request_record"
grep -F 'DASHSCOPE_API_KEY is not configured' "$temporary_directory/no-key.err" >/dev/null

rm -f "$request_record"
if QWEN_BASE_URL='https://example.com/compatible-mode/v1'     bash "$qwen_alibaba" chat test     >"$temporary_directory/untrusted.out" 2>"$temporary_directory/untrusted.err"; then
  printf 'qwen_alibaba test: untrusted API host unexpectedly succeeded\n' >&2
  exit 1
fi
test ! -e "$request_record"
grep -F 'refusing to send the API key to unapproved host'   "$temporary_directory/untrusted.err" >/dev/null

doctor=$(bash "$qwen_alibaba" doctor)
printf '%s\n' "$doctor" | grep -F $'model\tqwen3-coder-next' >/dev/null
printf '%s\n' "$doctor" | grep -F $'api_key\tconfigured' >/dev/null

printf 'qwen_alibaba tests passed\n'
