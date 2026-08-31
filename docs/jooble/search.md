# Job search

```text
POST https://jooble.org/api/{api_Key}
```

The API key is embedded in the path. Use the regional Jooble hostname associated with the key.

Request body parameters documented by Jooble:

- `keywords` — job-search keywords.
- `location` — search location.
- `radius` — optional radius in kilometers; documented values are 0, 4, 8, 16, 26, 40, and 80.
- `salary` — optional minimum salary.
- `page` — optional result page.
- `ResultOnPage` — optional page size.

## Regional keys

Jooble's current documentation says each country/domain requires a separate API key. For example, a `jooble.org` key is restricted to U.S. listings while the UK uses a key from `uk.jooble.org`.

## Free-plan quota

The current Help Center page states that the free REST plan permits **500 requests total over the lifetime of a key**.

Source: https://help.jooble.org/en/support/solutions/articles/60001448238 (modified 2026-08-16).
