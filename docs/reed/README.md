# Reed.co.uk APIs

Reed exposes distinct **Jobseeker** and **Recruiter** API families.

Official developer hub: https://www.reed.co.uk/developers
Jobseeker docs: https://www.reed.co.uk/developers/jobseeker
Recruiter docs: https://www.reed.co.uk/developers/recruiter

The Jobseeker API is the useful CLI-search surface: search all Reed jobs and retrieve a job by ID using an API key as the HTTP Basic username.

The Recruiter page currently marks its v1 job-posting APIs as legacy/deprecated and points to API V2. The same page still documents restricted CV-search endpoints. This branch records what the official site actually exposes and labels the lifecycle state explicitly.

## Files

- `endpoints.txt` — current Jobseeker routes plus documented legacy/restricted Recruiter routes.
- `jobseeker.md` — job search and details.
- `recruiter.md` — posting lifecycle, CV search, authentication, limits, and V2 caveat.
