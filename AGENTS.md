# Agent instructions

Apply the shared evidence and acceptance guardrails in
`isomorphisms/ai-ci/AGENTS.md`.

Before changing this repository, read its README and repository-local
documentation, inspect the current branch/worktree and nearby active work, and
preserve established architecture, terminology, source/build layout, and
explicit current human corrections.

Keep this file repository-specific. Add local rules as the project develops; do
not copy the shared `ai-ci` rulebook here.

## SMS service boundary

`bin/idric_sms_service` owns command-line invocation and filesystem state for the
SMS service slice. Idric-Net owns SMS meanings and the deterministic
`idric-sms-request` parser. Do not copy that parser into this repository or
replace it with a shell approximation just to make a local test pass.

A syntax check is not an SMS integration receipt. Full SMS acceptance must name
and execute the exact Idric-Net parser revision used by the test.

## Zillow boundary

Keep Zillow Research downloads, Zillow Mortgage partner calls, Bridge products,
Mortech products, Zillow Rentals integrations, and dotloop OAuth as distinct
interfaces. Do not flatten them into one generic request path.

Do not substitute consumer-site page parsing for an unavailable documented API.
Do not restore legacy ZWSID calls from old examples unless a current Zillow
source establishes that the endpoint is still supported.

The files under `docs/zillow/` document source locations and interface shape;
they are not live-service acceptance. A documentation fetch, CSV fixture, or
mock response does not establish permission, authentication, or successful API
execution.

## Grease consumer boundary

Grease is the consumer-facing shell language and command for the Grease-based
programs in this repository. Use `grease` in shebangs, examples, workflows,
receipts, and human-facing instructions. Do not invoke or name `ysh` as the
consumer runtime. The inherited Oils implementation is an internal Grease
implementation detail and belongs only in implementation/provenance discussion
inside the Grease/Oils repositories.
