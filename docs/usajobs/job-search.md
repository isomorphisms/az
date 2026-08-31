# Current job search

```text
GET https://data.usajobs.gov/api/Search
```

## Authentication

USAJOBS requires an API key for current-job searches. Official examples use request headers for:

- `Host: data.usajobs.gov`
- `User-Agent` — the email associated with the API key.
- `Authorization-Key` — the issued API key.

## Pagination

The Search API returns 250 job opportunity announcements per page by default. `ResultsPerPage` can be set up to 500; `Page` selects the page.

Example shape:

```text
GET /api/Search?Page=3&ResultsPerPage=50
```

The search API accepts numerous filters for keywords, location, occupational series/category, organization, hiring paths, salary, dates, and other federal-job attributes. The code-list endpoints in `bulk-and-codelists.md` provide valid values for many of those fields.

Source: https://developer.usajobs.gov/api-reference/
