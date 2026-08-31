# Realtime REST endpoints

Canonical reference: https://developers.openai.com/api/reference/overview

The Realtime product also uses WebRTC, WebSocket, SIP, and client/server event
messages. `endpoints.txt` inventories only the published REST paths used to
create credentials or manage calls. Event message types are protocol messages,
not extra REST URLs.

## Current Realtime routes

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/realtime/client_secrets` | Create a short-lived client secret plus Realtime session configuration. |
| POST | `/realtime/translations/client_secrets` | Create a short-lived client secret for Realtime translation. |
| POST | `/realtime/calls` | Create a WebRTC call and obtain the SDP answer required to finish negotiation. |
| POST | `/realtime/calls/{call_id}/accept` | Accept an incoming SIP call and configure its Realtime session. |
| POST | `/realtime/calls/{call_id}/hangup` | End an active Realtime call. |
| POST | `/realtime/calls/{call_id}/refer` | Transfer an active SIP call using REFER. |
| POST | `/realtime/calls/{call_id}/reject` | Reject an incoming SIP call. |

## Legacy Realtime Beta token routes

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/realtime/sessions` | Create an ephemeral token for the older Realtime session API. |
| POST | `/realtime/transcription_sessions` | Create an ephemeral token for the older Realtime transcription-session API. |

The current human reference places these two session-creation routes under
**Legacy → Realtime Beta**. They remain in the pinned OpenAPI specification and
therefore remain in the flat endpoint inventory.

## Events are not REST endpoints

The API reference separately documents:

- Realtime client events;
- Realtime server events;
- translation client events;
- translation server events.

Those names describe messages sent over an established realtime transport.
They should be inventoried separately if a later checkpoint needs an opcode/event
list, but they should not be mixed into `endpoints.txt` because they are not
independently callable HTTP resource paths.
