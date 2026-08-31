# Indeed APIs

Status: **current partner APIs are permissioned; there is no anonymous public replacement for the old Publisher Job Search API**.

Official catalog: https://docs.indeed.com/api-guides/
Getting started: https://docs.indeed.com/getstarted/integrate-and-call-apis
GraphQL schema: https://docs.indeed.com/api/graphql_schema

Indeed's current developer surface is not one simple REST job-search API. Most current partner capabilities use one GraphQL endpoint, with separate products/permissions for job ingestion, job updates, candidates, dispositions, sponsored jobs, employer data, and Hiring Lab data. REST/SSE surfaces exist for SCIM and real-time events.

Indeed's own current getting-started guide demonstrates a `jobSearch` GraphQL query but also demonstrates the response when a client has not been provisioned for the `job-retrieval-service`. Do not treat that query as an open public job-search service.

## Files

- `endpoints.txt` — HTTP routes plus named GraphQL operations so the shared GraphQL transport is not mistaken for a one-operation API.
- `jobs.md` — job creation/update/retrieval operations, rates, and the job-search access caveat.
- `candidates-and-dispositions.md` — Candidate Sync and Disposition Sync operations.
- `auth-rest-and-feeds.md` — OAuth, SCIM, SSE, XML/webhook interfaces, and access model.
