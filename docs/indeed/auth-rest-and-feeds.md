# Authentication, REST/SSE, XML, and webhook interfaces

## OAuth / OpenID Connect

Current Indeed authorization endpoints include:

```text
GET  https://secure.indeed.com/oauth/v2/authorize
POST https://apis.indeed.com/oauth/v2/tokens
GET  https://secure.indeed.com/v2/api/appinfo
GET  https://secure.indeed.com/v2/api/userinfo
GET  https://secure.indeed.com/.well-known/keys
```

Indeed supports client-credentials (2-legged) and authorization-code (3-legged) OAuth depending on the integration. Access tokens currently expire after 3,600 seconds.

## Real-time SSE

```text
GET https://apis.indeed.com/sse/notifications/message-update
```

This streams server-sent events and accepts an optional comma-separated `eventTypes` filter.

## SCIM 2.0

Base resource:

```text
https://api.indeed.com/scim/v2/Users
```

Current operations are GET/POST on the collection and GET/PUT/DELETE on `Users/{id}`. SCIM uses OAuth 2.0 client credentials and `application/scim+json`.

## XML and callbacks

The current API guide also documents:

- Indeed Apply.
- Job Sync XML.
- jobs lifecycle/application webhooks.

Those are integration protocols where the feed or callback URL can be partner/employer hosted or provisioned as part of the integration; they are not additional universal Indeed request endpoints that can truthfully be listed as one fixed URL. They are therefore documented here rather than fabricated in `endpoints.txt`.

## Access model

Becoming an Indeed partner provisions an app and its permissions. A valid OAuth token does not imply access to every GraphQL service. The old public Publisher Job Search API should not be assumed to be available for new integrations.

Sources:
- https://docs.indeed.com/api-guides/
- https://docs.indeed.com/authentication/auth-2-legged-oauth
- https://docs.indeed.com/authentication/auth-3-legged-oauth
- https://docs.indeed.com/support/troubleshoot-oauth-errors
- https://docs.indeed.com/api/real-time-api/stream-real-time-updates
- https://docs.indeed.com/scim-api/scim-api-guide
