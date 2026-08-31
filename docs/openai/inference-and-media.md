# Inference and media endpoints

Canonical reference: https://developers.openai.com/api/reference/overview

This file documents the ordinary application-facing inference and media paths in
`endpoints.txt`. Parameter and schema details should be taken from the pinned
OpenAPI specification named in `README.md`; the tables here describe what each
HTTP resource is for.

## Responses

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/responses` | Create a model response. This is OpenAI's currently promoted general-purpose inference surface. |
| GET, DELETE | `/responses/{response_id}` | Retrieve or delete a stored response. |
| POST | `/responses/{response_id}/cancel` | Cancel an in-progress background response. |
| GET | `/responses/{response_id}/input_items` | List the input items associated with a response. |
| POST | `/responses/input_tokens` | Count input tokens for a prospective Responses request. |
| POST | `/responses/compact` | Compact conversation state for continued use with Responses. |

The multi-agent beta duplicates of these routes are documented in
`legacy-and-beta.md` because the OpenAPI spec publishes them with literal
`?beta=true` path keys.

## Chat Completions

| Method | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/chat/completions` | List stored chat completions or create a chat completion. |
| GET, POST, DELETE | `/chat/completions/{completion_id}` | Retrieve, update, or delete a stored chat completion. |
| GET | `/chat/completions/{completion_id}/messages` | List messages belonging to a stored chat completion. |

For the `chat-latest` baseline use case, see `chat-latest.md`.

## Legacy text completions

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/completions` | Create a legacy prompt-style text completion. |

The human API reference currently files this surface under **Legacy**.

## Embeddings and moderation

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/embeddings` | Produce embedding vectors for supplied input. |
| POST | `/moderations` | Classify text and/or image input with a moderation model. |
| POST | `/content_provenance_checks` | Run a content-provenance check on submitted media. |

## Images

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/images/generations` | Generate images. |
| POST | `/images/edits` | Edit one or more supplied images. |
| POST | `/images/variations` | Create image variations. |

Streaming image events are event types emitted by these operations; they are not
additional REST paths and therefore are not separate lines in `endpoints.txt`.

## Audio

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/audio/speech` | Generate speech audio from text. |
| POST | `/audio/transcriptions` | Transcribe audio. |
| POST | `/audio/translations` | Translate supplied audio to text. |
| GET, POST | `/audio/voice_consents` | List or create custom-voice consent recordings. |
| GET, POST, DELETE | `/audio/voice_consents/{consent_id}` | Retrieve, update metadata for, or delete a voice consent. |
| POST | `/audio/voices` | Create a custom voice using an eligible consent recording. |

The custom-voice routes are included because they are present in the current
human reference and in the pinned OpenAPI snapshot; older SDK inventories can
miss them.

## Videos

| Method | Path | Purpose | Status in pinned spec |
| --- | --- | --- | --- |
| GET, POST | `/videos` | List video jobs or create a video generation job. | current |
| GET, DELETE | `/videos/{video_id}` | Retrieve metadata for or delete a video job. | current |
| GET | `/videos/{video_id}/content` | Download generated video content or a derived asset. | current |
| POST | `/videos/{video_id}/remix` | Create a remix from a completed video. | current |
| POST | `/videos/characters` | Create a reusable character from uploaded video. | deprecated flag present |
| GET | `/videos/characters/{character_id}` | Retrieve a video character. | deprecated flag present |
| POST | `/videos/edits` | Create a video job by editing source/generated video. | deprecated flag present |
| POST | `/videos/extensions` | Extend a completed video. | deprecated flag present |

The human reference may continue to display endpoints that carry a deprecation
flag in the machine-readable specification. This inventory records both facts
rather than treating navigation position as the deprecation authority.
