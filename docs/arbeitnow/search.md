# Job search

## Germany / Europe

```text
GET https://www.arbeitnow.com/api/job-board-api
```

## United Kingdom

```text
GET https://www.arbeitnow.co.uk/api/job-board-api
```

Authentication: none for the free API.

The current official article explicitly documents `visa_sponsorship=true|false`; its example is:

```text
https://www.arbeitnow.com/api/job-board-api?visa_sponsorship=true
```

The feed consolidates data from employer/ATS sources including Greenhouse, SmartRecruiters, JOIN, Teamtailor, Recruitee, and Comeet. Records include a `remote` field.

Arbeitnow also offers custom private API endpoints for paid customers; those are not enumerated here because they are customer-specific rather than a public fixed API surface.

Source: https://www.arbeitnow.com/blog/job-board-api (updated 2026-08-01).
