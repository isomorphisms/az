# Employment-market data and auxiliary endpoints

## Salary/vacancy data

```text
GET /v1/api/jobs/{country}/history
GET /v1/api/jobs/{country}/histogram
GET /v1/api/jobs/{country}/geodata
GET /v1/api/jobs/{country}/top_companies
```

These provide historical salary/vacancy series, current salary distributions, vacancies by sub-region, and top employers by vacancy count.

## Taxonomy

```text
GET /v1/api/jobs/{country}/categories
```

Returns Adzuna's category labels and tags.

## Salary prediction

```text
GET /v1/api/jobs/{country}/jobsworth
```

Returns a salary estimate from a title and descriptive text when Adzuna can produce one.

## Version

```text
GET /v1/api/jobs/{country}/version
```

Returns API/software version information.

## Default access limits

Adzuna's current terms list default limits of:

- 25 hits/minute
- 250 hits/day
- 1,000 hits/week
- 2,500 hits/month

The terms also impose attribution and licensing conditions, especially for ongoing commercial/government/academic use and for publishing listings or aggregate employment data. Read the current terms before building a persistent mirror.

Sources:
- https://developer.adzuna.com/docs/historical
- https://developer.adzuna.com/docs/histogram
- https://developer.adzuna.com/docs/regional
- https://developer.adzuna.com/docs/companies
- https://developer.adzuna.com/docs/categories
- https://developer.adzuna.com/docs/jobsworth
- https://developer.adzuna.com/docs/version
- https://developer.adzuna.com/docs/terms_of_service
