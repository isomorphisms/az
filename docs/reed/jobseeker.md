# Jobseeker API

## Search jobs

```text
GET https://www.reed.co.uk/api/1.0/search
```

Authentication: HTTP Basic with the Reed API key as username and an empty password.

Documented filters include employer ID/profile ID, keywords, location, distance, permanent/contract/temp, full-time/part-time, salary bounds, agency/direct employer, graduate roles, and pagination via `resultsToTake` and `resultsToSkip`. The current docs cap `resultsToTake` at 100.

## Job details

```text
GET https://www.reed.co.uk/api/1.0/jobs/{jobId}
```

Returns a detailed job record including employer, title, description, location, salary where visible, contract/job type, expiration date, external application URL when applicable, and Reed URL.

Source: https://www.reed.co.uk/developers/jobseeker
