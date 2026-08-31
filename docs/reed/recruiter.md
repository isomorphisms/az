# Recruiter APIs

## Lifecycle state

Reed's current developer page says the **legacy Job Posting APIs have been deprecated** and points to a newer API V2. The old v1 routes remain described/searchable and are retained here only as deprecated reference material:

```text
POST /recruiter/api/1.0/jobs
PUT  /recruiter/api/1.0/jobs/update/{jobId}
PUT  /recruiter/api/1.0/jobs/extend/{jobId}
PUT  /recruiter/api/1.0/jobs/end/{jobId}
PUT  /recruiter/api/1.0/jobs/relist/{jobId}
```

The linked V2 reference is https://www.reed.co.uk/api/documentation/ . Its endpoint listing was not retrievable while this inventory was prepared, so this branch does not invent V2 paths.

## CV Search API

Reed explicitly says access is **not provided as standard**. Documented routes are:

```text
GET /recruiter/api/1.0/cvsearch
GET /recruiter/api/1.0/cvsearch/candidate/{candidateId}/preview
GET /recruiter/api/1.0/cvsearch/cv/{candidateId}
GET /recruiter/api/1.0/cvsearch/candidate/{candidateId}
GET /recruiter/api/1.0/cvsearch/downloadlimits
GET /recruiter/api/1.0/cvsearch/searchavailability
```

## Recruiter authentication

Recruiter requests use a client ID, timestamp, and HMAC-SHA1 request signature derived from the API key plus the HTTP method, user-agent, full URL, host, and timestamp. Required headers include `X-ApiSignature`, `X-ApiClientId`, and `X-TimeStamp`.

The current page lists a default Recruiter API limit of 2,000 requests/hour, customizable by Reed.

Source: https://www.reed.co.uk/developers/recruiter
