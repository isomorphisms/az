# Campaign Management and conversion data

Campaign Management base URL:

```text
https://api.ziprecruiter.com/campaign-manager/v1
```

Authentication: HTTP Basic.

## Health and accounts

```text
GET /ping
GET /accounts
GET /account/{accountId}
```

## Campaigns

```text
GET  /account/{accountId}/campaigns
POST /account/{accountId}/campaign
GET  /account/{accountId}/campaign/{campaignId}
PUT  /account/{accountId}/campaign/{campaignId}
```

## Budgets

```text
GET  /account/{accountId}/campaign/{campaignId}/budget
POST /account/{accountId}/campaign/{campaignId}/budget
GET  /account/{accountId}/campaign/{campaignId}/budgets
GET  /account/{accountId}/campaign/{campaignId}/budget/{budgetId}
PUT  /account/{accountId}/campaign/{campaignId}/budget/{budgetId}
```

The current Campaign Management overview documents 5 concurrent requests per API key, a queue of 10, a 120-second timeout, and no batch endpoint.

## Conversion reporting

Partners can report external-apply conversions to:

```text
GET https://track.ziprecruiter.com/marketplace/v1/conversion
```

Required query data includes timestamp, ZipRecruiter click ID, apply ID, and vendor; optional fields include job ID, user-agent, paid flag, and default-campaign flag.

Other click/conversion data can be pushed by ZipRecruiter as CSV to partner-provided (S)FTP locations; those destinations are not fixed ZipRecruiter endpoints.

Sources:
- https://www.ziprecruiter.com/partner/documentation/campaign-manager/
- https://api.ziprecruiter.com/campaign-manager/v1/swaggerui/external_docs/Campaign_Management_API.pdf
- https://www.ziprecruiter.com/partner/documentation/data-integrations/
