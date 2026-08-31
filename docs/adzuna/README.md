# Adzuna Jobs API

Status: **public developer API requiring `app_id` and `app_key`**.

Official overview: https://developer.adzuna.com/overview
Terms: https://developer.adzuna.com/docs/terms_of_service

Adzuna is one of the useful general job-search backends in this survey because it exposes search plus employment-market data rather than only employer-side job posting.

## Documentation discrepancy

The current overview says the API consists of **nine endpoints**. The current prose navigation exposes eight named route families: search, historical data, histogram, regional/geodata, top companies, categories, Jobsworth, and version. The interactive endpoint definition was not loadable when this inventory was prepared, so `endpoints.txt` records the eight verifiable routes and explicitly leaves the discrepancy unresolved rather than guessing.

## Files

- `endpoints.txt` — literal verifiable route inventory.
- `search.md` — job-ad search and authentication.
- `market-data.md` — salary, vacancy, categories, Jobsworth, version, rates, and terms.
