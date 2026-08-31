# ZipRecruiter Partner APIs

Status: **partner-only integration toolkit; credentials are issued during onboarding**.

Official documentation: https://www.ziprecruiter.com/partner/documentation/
Authentication: https://www.ziprecruiter.com/partner/documentation/authentication/

The current toolkit covers job posting, screening questions, paid features, application delivery, hiring signals, embedded sponsorship, campaign management, and conversion reporting. It does **not** expose a general anonymous job-search endpoint comparable to the old public job-board APIs people sometimes refer to in older examples.

Most partner APIs use HTTP Basic authentication. The sponsorship flow uses a separate Basic credential to obtain a short-lived bearer token.

## Files

- `endpoints.txt` — fixed ZipRecruiter HTTP resources and methods.
- `jobs-questions-features.md` — organic job lifecycle and job-attached data.
- `applications-and-signals.md` — Apply Webhook, RDB, Hiring Signals, and feed directionality.
- `sponsorship.md` — token/status APIs, embed URLs, SDK versions, and limits.
- `campaigns-and-data.md` — campaign/budget endpoints and conversion reporting.
