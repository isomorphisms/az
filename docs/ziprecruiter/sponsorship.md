# Embedded Sponsorship API and SDK

Sponsorship credentials are separate from the Jobs API key.

## Token exchange

```text
POST https://api.ziprecruiter.com/ats-embed/v0/api/token
```

Uses the Sponsorship API Basic credential and returns a bearer token. Current docs give the token a two-hour (`7200` second) lifetime with no refresh-token flow; call token exchange again.

The current v1 SDK uses an equivalent v1 API route:

```text
POST https://api.ziprecruiter.com/ats-embed/v1/api/token
```

## Sponsorship status

```text
POST https://api.ziprecruiter.com/ats-embed/v0/api/sponsored_status
POST https://api.ziprecruiter.com/ats-embed/v1/api/sponsored_status
```

## Hosted flows

Current manual-integration docs show:

```text
GET https://api.ziprecruiter.com/ats-embed/v0/?jobId={JOB_ID}&token={TOKEN}
GET https://api.ziprecruiter.com/ats-embed/v0/manage?jobId={JOB_ID}&token={TOKEN}
```

## SDK

```text
GET https://api.ziprecruiter.com/ats-embed/v1/sdk.js
GET https://api.ziprecruiter.com/ats-embed/v0/sdk.js
```

`v1` is the current rolling SDK. `v0` is legacy but supported and frozen. ZipRecruiter says v1 calls the equivalent `/ats-embed/v1/api/...` routes while the documented v0 routes remain supported.

## Current limits

- Token exchange: 3,000 requests/minute per `client_id` and 100,000/hour per `client_id`.
- Sponsored status: 60 requests/minute per user.
- Status requests accept up to 50 job IDs.

Sources:
- https://www.ziprecruiter.com/partner/documentation/sponsorship-api/
- https://www.ziprecruiter.com/partner/documentation/sponsorship-sdk/
- https://www.ziprecruiter.com/partner/documentation/quickstart/
