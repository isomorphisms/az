# Jobicy API

Status: **public and usable without authentication**.

Official developer documentation: https://jobicy.com/jobs-rss-feed

The current public REST API is deliberately small: one endpoint returns Jobicy's latest remote-job listings. Jobicy also publishes an MCP server and RSS feed, which are recorded in `endpoints.txt` but kept distinct from the REST surface.

For a command-line job-search backend, this is one of the straightforward services in this inventory because it requires no partner approval or API key.

## Files

- `endpoints.txt` — literal endpoint inventory.
- `search.md` — REST parameters, response shape, and usage notes.
