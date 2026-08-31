# The Muse API

Status: **developer API with app registration; the public jobs route is currently reachable**.

Developer hub: https://www.themuse.com/developers
API terms: https://www.themuse.com/developers/api/v2/terms

The Muse's current terms say the API supplies listings of jobs, companies, coaches, and posts. During this inventory the jobs route returned current JSON successfully, while direct probes of the corresponding companies/coaches/posts route shapes returned server errors. Those three are marked `UNVERIFIED` in `endpoints.txt` rather than silently treated as working.

## Files

- `endpoints.txt` — confirmed and explicitly unverified route inventory.
- `jobs.md` — confirmed jobs endpoint and response notes.
- `status-and-terms.md` — registration, content families, and verification caveat.
