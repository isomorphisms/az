# Jooble REST API

Status: **publicly documented search API requiring a regional API key**.

Official documentation: https://help.jooble.org/en/support/solutions/articles/60001448238
API-key registration: https://jooble.org/api/about

Jooble exposes job search through a single POST route. API keys are regional: a key generated on one Jooble country/domain only searches that region.

The current free plan documentation states a lifetime quota of 500 requests per key, not a monthly quota.

## Files

- `endpoints.txt` — literal route pattern and regional note.
- `search.md` — request parameters, regional behavior, and quota.
