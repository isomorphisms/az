# Access, OAuth, and partner provisioning

## Restricted access

LinkedIn's current Job Posting API overview states that new Job Posting API partnerships are not being accepted. Access is limited to approved developers/partners under LinkedIn agreements; prospective users are directed to Apply Connect.

This makes LinkedIn unsuitable as a generic CLI job-search backend. It remains useful to document as a job-*posting* integration surface.

## OAuth

```text
POST https://www.linkedin.com/oauth/v2/accessToken
```

The Job Posting API uses OAuth 2.0 client credentials. Current Talent docs describe 30-minute bearer tokens.

## Customer provisioning

LinkedIn partners posting on behalf of multiple customers can use the Middleware Provisioning API:

```text
POST https://api.linkedin.com/v2/provisionedApplications
POST https://api.linkedin.com/v2/provisionedApplications/{developerApplicationURN}
GET  https://api.linkedin.com/v2/provisionedApplications?q=credentialsByUniqueForeignId&uniqueForeignId={uniqueForeignId}
```

These create, update, and retrieve child developer applications/credentials for customer accounts and themselves require specialized partner permissions.

Sources:
- https://learn.microsoft.com/en-us/linkedin/talent/job-postings/api/overview
- https://learn.microsoft.com/en-us/linkedin/talent/job-postings/api/job-posting-module1-basics?view=li-lts-2026-01
- https://learn.microsoft.com/en-us/linkedin/talent/middleware-platform/provisioning-api
