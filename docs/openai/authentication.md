# Authentication, scoping, and request metadata

Canonical reference: https://developers.openai.com/api/reference/overview

Snapshot: 2026-08-31.

## Base URL and API version

The pinned OpenAPI specification is OpenAPI 3.1.0, describes OpenAI API spec
version 2.3.0, and publishes this server:

```text
https://api.openai.com/v1
```

The public REST API major version is currently `v1`.

## Ordinary API credentials

Normal application requests use HTTP Bearer authentication:

```text
Authorization: Bearer $OPENAI_API_KEY
```

API keys are secrets. This repository should never contain a real key. Example
commands use environment-variable names only.

## Administration credentials

Administration routes under `/organization/...` and the administration-related
`/projects/...` role paths require an **Admin API key**. Use a distinct variable
in examples so an ordinary project key is not accidentally treated as sufficient:

```text
Authorization: Bearer $OPENAI_ADMIN_KEY
```

## Workload identity

OpenAI's current reference also supports short-lived bearer access tokens issued
through workload identity federation. These tokens authenticate requests in the
same `Authorization: Bearer ...` header. Workload identity federation is an
authentication mechanism, not an additional public REST resource path in the
pinned OpenAPI inventory.

## Organization and project scoping

For legacy user API keys that can address more than one organization/project,
OpenAI documents these optional request headers:

```text
OpenAI-Organization: $ORGANIZATION_ID
OpenAI-Project: $PROJECT_ID
```

Usage is attributed to the organization/project selected by the request.

## Request IDs

OpenAI returns a server-generated request identifier in:

```text
x-request-id
```

The client may also supply its own ASCII identifier with:

```text
X-Client-Request-Id: <client-generated-id>
```

The current reference says a client request ID may be at most 512 ASCII
characters. Capturing both identifiers is useful for API checkpoint fixtures and
for diagnosing timeouts where the response headers never reach the caller.

## Rate-limit headers

Common response headers documented by OpenAI include:

```text
x-ratelimit-limit-requests
x-ratelimit-limit-tokens
x-ratelimit-remaining-requests
x-ratelimit-remaining-tokens
x-ratelimit-reset-requests
x-ratelimit-reset-tokens
x-ratelimit-limit-project-tokens
x-ratelimit-remaining-project-tokens
x-ratelimit-reset-project-tokens
```

These are response metadata, not endpoints.

## Reproducible requests

For checkpoint work, save at least:

- endpoint and HTTP method;
- UTC request time;
- model ID/alias if applicable;
- request body after secret removal;
- organization/project scope if it affects behavior;
- HTTP status;
- `x-request-id`;
- returned model/snapshot identifiers where available;
- raw response body.

Never commit `Authorization` header values or reusable client secrets.
