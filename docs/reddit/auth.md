# Authentication and access

## OAuth support endpoints

- `/api/v1/authorize`
- `/api/v1/authorize.compact`
- `/api/v1/access_token`
- `/api/v1/revoke_token`

These four paths are included in `endpoints.txt` even though they are support
routes for OAuth rather than entries in the auto-generated Data API method
index.

Reddit's OAuth documentation distinguishes the hosts:

- Authorization and token exchange/revocation: `www.reddit.com`
- Bearer-token API calls: `oauth.reddit.com`

The historical OAuth documentation says access tokens expire after one hour
and describes refresh tokens, script applications, installed applications, and
client-credential flows. Treat those flow details as implementation references,
not as a guarantee that Reddit will approve a new Data API application today.

## Current policy boundary

As of this snapshot, Reddit's API landing page says:

- the Developer Platform is the official route for new apps;
- the Data API is legacy;
- some existing applications continue to use it;
- developers seeking new Data API access must request it under Reddit's current
  access process.

So a future `reddit` command should fail explicitly when credentials/access are
unavailable. It should not silently fall back to scraping private or
undocumented web endpoints.

## Secrets

If OAuth support is implemented later, client secrets, refresh tokens, and
access tokens belong in local configuration/state, not in this repository.
That matches the existing `az` rule that Amazon credentials are never committed.

Sources:

- https://old.reddit.com/wiki/api
- https://github.com/reddit-archive/reddit/wiki/OAuth2
- https://www.reddit.com/dev/api/
