# Job posting lifecycle

Current versioned route:

```text
POST https://api.linkedin.com/rest/simpleJobPostings
```

Legacy/unversioned route still documented during migration:

```text
POST https://api.linkedin.com/v2/simpleJobPostings
```

A single POST route performs lifecycle actions according to `jobPostingOperationType`. Current documentation covers at least `CREATE`, `UPDATE`, `RENEW`, and `CLOSE`; release notes also describe `UPGRADE` and `DOWNGRADE` for promotion state.

Current Talent API examples use `LinkedIn-Version: 202603` with the `/rest/` route. The `/v2/` route is being replaced by versioned REST endpoints; LinkedIn's migration material gives January 2027 as the migration deadline for the older simple-job-posting route.

Documentation also shows authenticated GET requests to `/rest/simpleJobPostings` and `/v2/simpleJobPostings` in the basic module.

The API supports asynchronous batch submission, with current create-job documentation limiting a batch to 100 jobs.

Sources:
- https://learn.microsoft.com/en-us/linkedin/talent/job-postings/api/sync-job-postings?view=li-lts-2026-03
- https://learn.microsoft.com/en-us/linkedin/talent/job-postings/api/create-jobs?view=li-lts-2026-03
- https://learn.microsoft.com/en-us/linkedin/talent/job-postings/job-posting-release-notes?view=li-lts-2026-03
