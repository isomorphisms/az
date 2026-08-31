# API status and terms

The Muse API Terms explicitly describe Muse Content as listings of **jobs, companies, coaches, and posts**, and require app registration. They also require Muse Content displayed through an app to link back to The Muse and prohibit scraping as a substitute for API access.

During verification on 2026-08-31:

- `/api/public/jobs?page=1` returned JSON successfully.
- `/api/public/companies?page=1` returned a server/internal error.
- `/api/public/coaches?page=1` returned a server/internal error.
- `/api/public/posts?page=1` returned a server/internal error.

Because the current developer API reference is too large for the documentation fetch used during this inventory, the three non-job route shapes are recorded as `UNVERIFIED` rather than asserted as current supported endpoints. This branch should be updated if The Muse's endpoint reference becomes directly inspectable.

Sources:
- https://www.themuse.com/developers
- https://www.themuse.com/developers/api/v2/terms
