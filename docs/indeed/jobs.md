# Jobs APIs

## Shared GraphQL transport

```text
POST https://apis.indeed.com/graphql
```

Most job-related operations use OAuth bearer tokens issued to an approved Indeed partner app.

## Job Sync

Current documented operations include:

```text
jobsIngest.createSourcedJobPostings
node
nodes
jobsIngest.expireSourcedJobsBySourcedPostingId
```

`createSourcedJobPostings` creates, upserts, or reactivates jobs. Expiration is explicit; Indeed does not automatically expire jobs submitted through Job Sync.

Current create/upsert rate limits depend on jobs per request:

- 1 job: 150/s, 4,000/min, 20,000/10 min, 80,000/hour.
- 2–10 jobs: 30/s, 800/min, 4,000/10 min, 16,000/hour.
- >10 jobs: 1/s, 40/min, 200/10 min, 800/hour.
- `expireSourcedJobsBySourcedPostingId` is currently exempt from these limits.

## Job Update

Current documented operations include:

```text
findEmployerJobsPartner
jobsIngest.updateSourcedJobPostings
jobsIngest.clearSourcedJobPostingUpdates
node
nodes
```

The current GraphQL schema also contains seat-management mutations:

```text
jobsIngest.addSeats
jobsIngest.updateSeats
jobsIngest.clearSeats
```

Current Job Update limits documented by Indeed include 5 requests/second for `findEmployerJobsPartner` and 20 requests/second total across update/clear mutations.

## Job search caveat

Indeed's current authentication/getting-started documentation shows:

```text
jobSearch(...)
```

on the same GraphQL endpoint. The example response also explicitly shows a client that lacks access to the `job-retrieval-service`. Therefore `jobSearch` is recorded in the inventory as a permissioned GraphQL operation, **not** as an open public job-search API.

## Hiring Lab

```text
findHiringLabPostingsPublic
```

Despite `Public` in the operation name, calls require an Indeed-issued Hiring Lab API key in the `Indeed-API-Key` header. It returns labor-market posting indices/data rather than ordinary full job-search results.

Sources:
- https://docs.indeed.com/job-sync-api/job-sync-api-guide
- https://docs.indeed.com/job-update-api/job-update-api-guide
- https://docs.indeed.com/getstarted/integrate-and-call-apis
- https://docs.indeed.com/hiring-lab-api/
- https://docs.indeed.com/api/graphql_schema
