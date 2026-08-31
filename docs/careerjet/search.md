# Job search

```text
GET https://search.api.careerjet.net/v4/query
```

## Authentication

HTTP Basic authentication:

- username: Careerjet publisher API key
- password: empty string

## Request context

Careerjet's current examples require the end user's `user_ip` and `user_agent` parameters and require a `Referer` header identifying the publisher page that initiated the query.

Common query parameters include:

- `locale_code` — language/country locale, such as `en_US`.
- `keywords` — search terms.
- `location` — search location.
- `user_ip` — actual user IP.
- `user_agent` — actual user-agent string.

The publisher API key is issued per publisher website, so this is not an anonymous/open endpoint despite being a job-search API.

Source: https://www.careerjet.com/partners/api
