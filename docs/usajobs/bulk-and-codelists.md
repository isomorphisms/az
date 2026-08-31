# Historic announcements, announcement text, and code lists

## Historic JOAs

```text
GET https://data.usajobs.gov/api/historicjoa
```

No authentication required. This endpoint provides smaller fields for current and past job opportunity announcements and is designed for bulk retrieval. It supports filters including agency/department, occupational series, announcement/control numbers, and open/close date ranges. Pagination uses continuation tokens and currently returns a page size of 1,000 in the documented examples.

## Announcement Text

```text
GET https://data.usajobs.gov/api/historicjoa/announcementtext
```

No authentication required. This companion endpoint returns the long text fields for current and past announcements and uses the same style of filters and continuation-token pagination.

## Code lists

All current code-list routes are listed in `endpoints.txt`. They require no authentication and generally accept an optional `lastmodified` parameter. They cover agencies, occupational series, pay plans, locations, countries, schedules, hiring paths, document/application metadata, security clearances, service types, and other controlled vocabularies.

Sources:
- https://developer.usajobs.gov/api-reference/
- https://developer.usajobs.gov/api-reference/get-api-historicjoa
- https://developer.usajobs.gov/api-reference/get-api-announcementtext
- https://developer.usajobs.gov/tutorials/code-list
