# Reddit API inventory

Snapshot: 2026-08-31.

`endpoints.txt` is the flat machine-friendly inventory: one canonical path per
line, lexicographically sorted, with no comments. It contains 235 paths:

- 231 canonical paths from Reddit's current auto-generated legacy Data API
  reference.
- 4 OAuth support paths documented separately by Reddit's OAuth documentation:
  authorization, compact authorization, access-token, and token revocation.

## Scope

This inventory is for the externally callable Reddit Data API surface relevant
to a command-line client. It does **not** try to enumerate:

- Devvit app-local `/api/*` routes written by individual apps.
- Devvit `/external/*` callback routes.
- Reddit Ads API routes.
- Undocumented web-application/internal endpoints.

Canonical reference:

- https://www.reddit.com/dev/api/
- https://old.reddit.com/wiki/api
- https://github.com/reddit-archive/reddit/wiki/OAuth2

## Current access status

Reddit's current API landing page says the official path for new development is
the Developer Platform. It describes the Data API as legacy, says some existing
apps still use it, and directs new Data API applicants toward approved use
cases, especially moderation. Therefore this branch documents the Data API
surface without assuming that a new general-purpose CLI will automatically be
granted credentials.

That distinction matters for the later implementation: the existence of
`/search` in the reference does not by itself imply unrestricted new-client
access.

## Path notation

The auto-generated reference uses words such as `subreddit`, `username`,
`article`, `thread`, `page`, `multipath`, and `srname` as path placeholders.

Some endpoint families are optionally scoped under `/r/{subreddit}` even when
the compact index prints only the suffix. For example the search detail is
shown as:

`GET [/r/subreddit]/search`

The `.json` representation suffix commonly used by Reddit clients is not
treated as a separate endpoint here.

Authenticated Data API calls use `oauth.reddit.com` as the host after OAuth;
the OAuth authorization/token endpoints themselves are on `www.reddit.com`.

## Files

- `search-and-listings.md` — the read/search surface most relevant to a CLI.
- `auth.md` — OAuth support endpoints and access caveats.
- `accounts-users-messages.md` — identity, user history, messages, announcements.
- `subreddits-moderation.md` — subreddit metadata, relationships, mod queues,
  modmail, and mod notes.
- `content-and-presentation.md` — posts/comments, voting/saving, flair, emoji,
  widgets, captcha.
- `live-multis-wiki.md` — live threads, multireddits/filters, and wiki.
