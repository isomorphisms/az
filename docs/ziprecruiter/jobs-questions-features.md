# Jobs, Questions, and Features APIs

All three families use ZipRecruiter-issued Partner API credentials with HTTP Basic authentication.

## Jobs

```text
POST   https://api.ziprecruiter.com/partner/v0/job
PUT    https://api.ziprecruiter.com/partner/v0/job
PUT    https://api.ziprecruiter.com/partner/v0/job/{JOB_ID}
GET    https://api.ziprecruiter.com/partner/v0/job/{JOB_ID}
DELETE https://api.ziprecruiter.com/partner/v0/job/{JOB_ID}
```

`POST` creates a job. `PUT` updates/reopens it. `GET` retrieves it. `DELETE` closes/removes it from search. The current API supports U.S., Canadian, and Australian job country codes.

## Screening questions

```text
POST   /partner/v0/job/{JOB_ID}/questions
PUT    /partner/v0/job/{JOB_ID}/questions
GET    /partner/v0/job/{JOB_ID}/questions
DELETE /partner/v0/job/{JOB_ID}/questions
```

Questions can alternatively be supplied in the XML job feed.

## Paid features

```text
POST /partner/v0/job/{JOB_ID}/features
GET  /partner/v0/job/{JOB_ID}/features
```

The current documented feature is TrafficBoost (`single`, `double`, or `triple`). Once applied, the TrafficBoost level cannot be removed or changed through this API.

Sources:
- https://www.ziprecruiter.com/partner/documentation/job-api/
- https://www.ziprecruiter.com/partner/documentation/question-api/
- https://www.ziprecruiter.com/partner/documentation/features-api/
