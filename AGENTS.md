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
