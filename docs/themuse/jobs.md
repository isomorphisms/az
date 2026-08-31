# Jobs

Confirmed live on 2026-08-31:

```text
GET https://www.themuse.com/api/public/jobs?page=1
```

The JSON response is paginated and currently includes fields such as page metadata, job name/title, HTML contents, publication date, locations, levels, tags, company data, and a canonical Muse landing-page URL.

The developer site asks API users to register their app. Do not assume that the fact that a direct jobs request currently succeeds without an auth header means registration requirements can be ignored for a production integration.

Sources:
- https://www.themuse.com/developers
- https://www.themuse.com/developers/api/v2/terms
- https://www.themuse.com/api/public/jobs?page=1
