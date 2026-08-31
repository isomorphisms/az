# Search and categories

## Jobs

```text
GET https://remotive.com/api/remote-jobs
```

Optional query parameters:

- `category` — category name or slug.
- `company_name` — case-insensitive partial company-name filter.
- `search` — case-insensitive partial search over title and description.
- `limit` — maximum result count.

## Categories

```text
GET https://remotive.com/api/remote-jobs/categories
```

Authentication: none documented for the public API.

## Rate and use restrictions

Remotive recommends fetching only a few times per day (up to about four) and says more than two requests per minute will be blocked. Public jobs are delayed by 24 hours.

Redistribution is constrained: consumers must link back to the Remotive listing and identify Remotive as the source. Remotive explicitly forbids submitting its API jobs to third-party job sites including Jooble, Google Jobs, and LinkedIn Jobs. It also forbids using the listings merely to collect signups/email addresses before showing them.

Sources:
- https://remotive.com/remote-jobs/api
- https://github.com/remotive-com/remote-jobs-api
