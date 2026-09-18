# Alibaba Model Studio / Qwen boundary

This branch adds the first direct Alibaba Cloud Model Studio adapter for the
planned conservative repository-maintenance worker.

The initial target is exactly `qwen3-coder-next`. Do not silently substitute a
smaller coding model merely to reduce cost. An operator can change
`QWEN_MODEL` explicitly for experiments, but a request body cannot override the
configured model behind the operator's back.

## Interface

`bin/qwen_alibaba` uses Alibaba Model Studio's OpenAI-compatible
`POST /chat/completions` interface.

The default base URL is the Singapore international shared endpoint:

```text
https://dashscope-intl.aliyuncs.com/compatible-mode/v1
```

Alibaba recommends workspace-specific domains for production. For Singapore the
shape is:

```text
https://WORKSPACE_ID.ap-southeast-1.maas.aliyuncs.com/compatible-mode/v1
```

The adapter accepts only known Alibaba Model Studio host families before sending
the API key. It deliberately does not accept an arbitrary OpenAI-compatible URL
because this command owns a Model Studio credential.

Official references:

- https://www.alibabacloud.com/help/en/model-studio/qwen3-coder-next
- https://www.alibabacloud.com/help/en/model-studio/qwen-coder
- https://www.alibabacloud.com/help/en/model-studio/base-url
- https://www.alibabacloud.com/help/en/model-studio/qwen-api-via-openai-chat-completions

As of 2026-09-18, Alibaba documents `qwen3-coder-next` with a 262,144-token
context window, a 204,800-token maximum input, and a 65,536-token maximum
output. Those are provider claims, not local acceptance evidence.

## Configuration

Copy the example outside the repository:

```sh
mkdir -p ~/.config/az
cp config/qwen-secret.example ~/.config/az/qwen-secret
chmod 600 ~/.config/az/qwen-secret
$EDITOR ~/.config/az/qwen-secret
```

The secret file needs:

```text
DASHSCOPE_API_KEY=...
```

The API key and base URL must belong to the same Model Studio region.

## Commands

Simple text request:

```sh
ysh bin/qwen_alibaba chat 'Inspect this patch for a narrow correctness bug.'
```

Prompt from standard input:

```sh
git diff | ysh bin/qwen_alibaba chat
```

Raw OpenAI-compatible Chat Completions request, preserving the complete
response including token usage and any provider-returned metadata:

```sh
ysh bin/qwen_alibaba request request.json
cat request.json | ysh bin/qwen_alibaba request -
```

Raw Responses API request:

```sh
ysh bin/qwen_alibaba response response.json
cat response.json | ysh bin/qwen_alibaba response -
```

The raw request paths are intentional. The later agent loop will need to own
its conversation state instead of hiding it behind a convenience prompt wrapper.

## Live Grease endpoint probes

The branch includes two opt-in Grease/YSH scripts:

```sh
ysh scripts/qwen-alibaba-chat-live.ysh
ysh scripts/qwen-alibaba-responses-live.ysh
```

or through Make:

```sh
make test-qwen-live
```

These are paid live calls and therefore are not dependencies of `make test`.
Each sends a very small sentinel prompt through the configured
`qwen3-coder-next` endpoint and emits a compact receipt containing the
requested/returned model, response ID, token counts, and endpoint-specific
status.

The Chat Completions probe establishes only live text generation through
`/chat/completions`.

The Responses probe is intentionally separate. Alibaba's current Responses API
documentation says text-generation models outside its full-feature list receive
only basic compatibility. A successful
`scripts/qwen-alibaba-responses-live.ysh` run therefore establishes only that
the exact configured `qwen3-coder-next` endpoint accepted a basic Responses
text request. It does not establish built-in tools, custom tool calling, session
caching, or other Responses agent features.

## Tool-use uncertainty

Do not currently claim that Alibaba-hosted `qwen3-coder-next` has accepted
OpenAI function/tool calling.

Alibaba's current model-specific `qwen3-coder-next` page labels Function
Calling unsupported. The broader Qwen-Coder documentation describes tool-call
flows for coder models. Those two documentation surfaces are not sufficient to
promote tool calling to an accepted capability for this exact hosted model.

The first live acceptance should therefore record the exact model, endpoint,
request and response and separately determine whether tool calls are returned.
Until then, the repository-maintenance design should assume an external
supervisor can drive commands and feed observations back as ordinary messages.

## Evidence boundary

`test/qwen-alibaba-test.sh` uses a fake HTTP executable. It checks:

- the exact default model;
- request construction;
- extraction of assistant text;
- preservation of raw JSON responses;
- rejection of a request that tries to switch models;
- refusal to send the key to an unapproved host;
- missing-key failure.

That test proves local request construction and credential-host policy only. It
does not prove that a real API key authenticates, that Alibaba serves the
configured model in the selected region, that inference succeeds, that tool
calling works, or that the model is suitable for autonomous GitHub actions.

A live paid request is a separate acceptance stage and must not be inferred from
the fake-transport test.

## Repository-maintenance boundary

The model endpoint is not the GitHub authority. The planned background worker
should keep GitHub credentials in a deterministic supervisor, with the model
limited to proposing work and producing evidence. Merge authorization, exact
head checks, protected-path rules, and other irreversible actions remain
separate gates. This preserves the shared `ai-ci` evidence boundary.
