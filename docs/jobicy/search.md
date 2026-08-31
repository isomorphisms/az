# Search and discovery

## REST jobs endpoint

```text
GET https://jobicy.com/api/v2/remote-jobs
```

Authentication: none.

Optional query parameters documented by Jobicy:

- `count` — number of results, 1–200; default 200.
- `geo` — location slug such as `usa`, `europe`, or `apac`.
- `industry` — category slug such as `engineering`, `marketing`, or `data-science`.
- `tag` — keyword search over available job content.

The JSON response contains a `jobs` array. Job records include an ID, canonical Jobicy URL, title, company, categories, employment type, geographic eligibility, seniority, description, publication date, and salary fields when supplied.

Jobicy asks integrations to retain the original source URL when displaying a listing.

## Other documented interfaces

- MCP server: `https://jobicy.com/mcp`; current tools include `get_jobs` and `get_taxonomies`.
- RSS: `https://jobicy.com/jobs/feed`.

Source: https://jobicy.com/jobs-rss-feed (page says updated 2026-07-30).
