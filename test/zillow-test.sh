#!/usr/bin/env bash
set -euo pipefail

script_directory=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
repository_directory=$(CDPATH= cd -- "$script_directory/.." && pwd)
temporary_directory=$(mktemp -d)
trap 'rm -rf "$temporary_directory"' EXIT

expected_dataset='zhvi-metro-all-homes-mid-tier-smoothed-seasonally-adjusted-monthly'
expected_url='https://files.zillowstatic.com/research/public_csvs/zhvi/Metro_zhvi_uc_sfrcondo_tier_0.33_0.67_sm_sa_month.csv'
request_record="$temporary_directory/request"

cat > "$temporary_directory/icu" <<'FAKE_ICU'
#!/bin/sh
printf '%s\n' "$*" > "$ZILLOW_TEST_REQUEST_RECORD"
printf '%s\n' 'RegionID,RegionName' '1,Example'
FAKE_ICU
chmod +x "$temporary_directory/icu"

actual_url=$(ICU="$temporary_directory/icu" bash "$repository_directory/bin/zillow" research url "$expected_dataset")
test "$actual_url" = "$expected_url"

download_output=$(ZILLOW_TEST_REQUEST_RECORD="$request_record" ICU="$temporary_directory/icu" \
  bash "$repository_directory/bin/zillow" research download "$expected_dataset")
test "$download_output" = $'RegionID,RegionName\n1,Example'
test "$(cat "$request_record")" = "get $expected_url"

rm -f "$request_record"
if ZILLOW_TEST_REQUEST_RECORD="$request_record" ICU="$temporary_directory/icu" \
    bash "$repository_directory/bin/zillow" research download unsupported-dataset \
    >"$temporary_directory/unsupported.out" 2>"$temporary_directory/unsupported.err"; then
  printf 'zillow test: unsupported dataset unexpectedly succeeded\n' >&2
  exit 1
fi
test ! -e "$request_record"
grep -q 'unsupported research dataset' "$temporary_directory/unsupported.err"

ICU="$temporary_directory/icu" bash "$repository_directory/bin/zillow" doctor \
  >"$temporary_directory/doctor.out"
grep -q '^research_dataset[[:space:]]' "$temporary_directory/doctor.out"

printf 'zillow tests passed\n'
