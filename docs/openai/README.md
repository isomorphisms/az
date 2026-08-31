# OpenAI API inventory

Snapshot: 2026-08-31.

`endpoints.txt` is the flat machine-friendly inventory: one published REST path
key per line, lexicographically sorted, with no comments. This snapshot contains
182 path keys.

## Provenance

Primary machine-readable source:

- OpenAI OpenAPI 3.1 specification, API spec version 2.3.0
- pinned commit: `690521b1753dce0c6d6b275f583d22537679cff9`
- https://github.com/openai/openai-openapi/blob/690521b1753dce0c6d6b275f583d22537679cff9/openapi.yaml
- base server: `https://api.openai.com/v1`

Human-readable cross-check:

- https://developers.openai.com/api/reference/overview
- https://developers.openai.com/api/docs/models/chat-latest

The pinned OpenAPI file is the source of truth for the flat inventory. The
current human reference is used to explain grouping, access requirements,
deprecations, and newer product names.

## Scope

Included:

- model inference and media generation
- Responses and Conversations
- Chat Completions and legacy Completions
- files, uploads, containers, vector stores, ChatKit, and Skills
- Evals, fine-tuning, batches, and model metadata
- Realtime REST setup/call-management routes
- organization and project administration
- deprecated REST routes that remain published in the pinned OpenAPI spec
- the six `?beta=true` Responses path keys published by that spec

Not counted as separate REST endpoints:

- server-sent streaming event names
- WebSocket client/server event types
- webhook event schemas delivered to a caller-owned URL
- SDK helper methods that do not issue a distinct HTTP request
- model IDs; models are data accepted by endpoints, not endpoints themselves

## Deprecation and oddities

The current human API sidebar no longer presents the old Assistants/Threads API,
but the pinned OpenAPI specification still publishes those routes and marks the
Assistants operations deprecated. They are retained here so this is an inventory
of the published API rather than only the currently promoted surface.

`/realtime/sessions` and `/realtime/transcription_sessions` are currently filed
under Realtime Beta / Legacy in the human reference.

The OpenAPI specification also publishes several project role-assignment paths
under `/projects/...` rather than `/organization/projects/...`. `endpoints.txt`
preserves the paths exactly as published instead of normalizing them.

The multi-agent Responses beta is represented in the OpenAPI document by path
keys containing `?beta=true`. Although query parameters are normally modeled
separately in OpenAPI, this inventory preserves those keys literally.

## Authentication

Normal application calls use bearer authentication:

```text
Authorization: Bearer $OPENAI_API_KEY
```

Administration calls require an Admin API key. OpenAI also documents short-lived
workload-identity access tokens. Legacy multi-organization/project keys can use
`OpenAI-Organization` and `OpenAI-Project` request headers.

See `authentication.md`.

## Files

- `endpoints.txt` — complete flat path inventory from the pinned OpenAPI spec.
- `chat-latest.md` — the ChatGPT-Instant baseline use case that motivated this branch.
- `inference-and-media.md` — Responses, Chat Completions, embeddings, moderation,
  images, audio, video, and provenance checks.
- `state-and-storage.md` — Conversations, files, uploads, vector stores,
  containers, ChatKit, and Skills.
- `training-evals-batches.md` — Evals, fine-tuning, batches, and models.
- `realtime.md` — REST setup/call routes and the event/transport distinction.
- `administration.md` — organization/project administration routes.
- `legacy-and-beta.md` — deprecated Assistants/Threads and beta Responses routes.
- `authentication.md` — credentials, scoping headers, request IDs, and versioning.

## Maintenance rule

When refreshing this inventory, compare against a specific OpenAI OpenAPI commit,
record that commit here, regenerate the sorted path list, and then cross-check the
current human API reference. Do not silently delete deprecated routes merely
because they disappear from navigation; remove them only when the authoritative
published specification removes them.
