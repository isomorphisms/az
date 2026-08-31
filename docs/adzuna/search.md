# Job-ad search

```text
GET https://api.adzuna.com/v1/api/jobs/{country}/search/{page}
```

All API calls require query parameters:

- `app_id`
- `app_key`

The search API accepts filters including keywords, exclusion keywords, location, salary, employment time/type, category, result count, and sorting. Results contain Adzuna job objects with fields such as title, description snippet, location, company, salary bounds, contract attributes, creation time, Adzuna ID, and a redirect URL.

Responses can be selected through HTTP `Accept` or the `content-type` query parameter; the official overview documents JSON, JSONP, XML, HTML, and some XLSX output.

Sources:
- https://developer.adzuna.com/overview
- https://developer.adzuna.com/docs/search
