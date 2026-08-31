# Legacy and beta endpoints

This file isolates paths that are still published in the pinned OpenAPI snapshot
but are deprecated, legacy, or represented as explicit beta variants.

## Deprecated Assistants API

The current human API sidebar no longer presents the Assistants/Threads REST API
as a normal current surface. The pinned OpenAPI specification still publishes
these routes and marks Assistants operations deprecated, so they remain in
`endpoints.txt`.

### Assistants

| Methods | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/assistants` | List or create assistants. |
| GET, POST, DELETE | `/assistants/{assistant_id}` | Retrieve, update, or delete an assistant. |

### Threads and messages

| Methods | Path | Purpose |
| --- | --- | --- |
| POST | `/threads` | Create a thread. |
| GET, POST, DELETE | `/threads/{thread_id}` | Retrieve, update, or delete a thread. |
| GET, POST | `/threads/{thread_id}/messages` | List or create thread messages. |
| GET, POST, DELETE | `/threads/{thread_id}/messages/{message_id}` | Retrieve, update, or delete a message. |

### Runs and run steps

| Methods | Path | Purpose |
| --- | --- | --- |
| POST | `/threads/runs` | Create a thread and immediately start a run. |
| GET, POST | `/threads/{thread_id}/runs` | List runs or create a run on an existing thread. |
| GET, POST | `/threads/{thread_id}/runs/{run_id}` | Retrieve or update a run. |
| POST | `/threads/{thread_id}/runs/{run_id}/cancel` | Cancel a run. |
| POST | `/threads/{thread_id}/runs/{run_id}/submit_tool_outputs` | Submit required tool-call outputs and continue the run. |
| GET | `/threads/{thread_id}/runs/{run_id}/steps` | List run steps. |
| GET | `/threads/{thread_id}/runs/{run_id}/steps/{step_id}` | Retrieve a run step. |

These routes require the legacy Assistants beta semantics documented in the
OpenAPI examples. New work should use Responses/Conversations rather than this
surface unless historical compatibility is the point of the test.

## Realtime Beta legacy routes

The current human reference places these beneath **Legacy → Realtime Beta**:

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/realtime/sessions` | Create an ephemeral token for the older Realtime session API. |
| POST | `/realtime/transcription_sessions` | Create an ephemeral token for the older realtime-transcription session API. |

See `realtime.md` for current Realtime client-secret and call-management routes.

## Legacy Completions

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/completions` | Legacy prompt-style text completion. |

## Multi-agent Responses beta

The pinned OpenAPI document publishes six additional path keys whose path text
literally includes `?beta=true`. They mirror the corresponding Responses
operations using the multi-agent beta schema.

| Method | Published path key | Stable analogue |
| --- | --- | --- |
| POST | `/responses?beta=true` | `/responses` |
| GET, DELETE | `/responses/{response_id}?beta=true` | `/responses/{response_id}` |
| POST | `/responses/{response_id}/cancel?beta=true` | `/responses/{response_id}/cancel` |
| GET | `/responses/{response_id}/input_items?beta=true` | `/responses/{response_id}/input_items` |
| POST | `/responses/input_tokens?beta=true` | `/responses/input_tokens` |
| POST | `/responses/compact?beta=true` | `/responses/compact` |

Query parameters are normally modeled separately from an OpenAPI path key. The
source specification deliberately publishes these beta variants this way, so
the flat inventory retains the literal strings. A future refresh should follow
the source specification rather than assuming this representation will persist.

## Deprecation policy for this repository

Do not remove a route from `endpoints.txt` just because it is deprecated or
removed from the human navigation. The point of the flat file is to answer
"what does the pinned published specification contain?" Mark lifecycle status
in Markdown and remove the path only when the authoritative machine-readable
specification removes it.
