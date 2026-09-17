# Zillow API notes

Snapshot: 2026-09-17

## The important boundary

There is no single current, open Zillow property-search API corresponding to the
consumer Zillow web application. The current Zillow Group developer portal is a
catalog of several different systems owned or operated through Zillow Group,
Bridge, Mortech, dotloop, and Zillow Rentals.

Do not turn an unavailable API into an HTML scraper and call that equivalent.
A Zillow consumer-page scraper would be a separate adapter with separate terms,
failure modes, and evidence.

## Best first command-line slice

The lowest-friction current Zillow data source is Zillow Research data:

<https://www.zillow.com/research/data/>

It is downloadable CSV and includes regional housing metrics at several
geographic levels. That makes it a better first unauthenticated `zillow` command
than pretending the commercial Zestimate/Public Records surfaces are public.
It is also directly relevant to tract/ZIP/city/metro comparisons.

A later authenticated slice can add documented interfaces only after credentials
or partner access actually exist.

## Access classes are not interchangeable

Keep these categories explicit in command names, configuration, tests, and
receipts:

- **Public download:** Zillow Research neighborhood/real-estate metrics.
- **Partner ID:** Zillow Mortgage `getRates` and `zillowLenderReviews`.
- **Bridge approval/token:** Agent Reviews, MLS Listings, Public Records, and Zestimates.
- **Mortech partner/MSA:** prospect, rate, LOS, lead-posting, and administration/integration products.
- **Zillow Rentals integration approval:** rental listing feeds and lead callbacks.
- **dotloop OAuth:** transaction-management resources.

A documentation page existing is not evidence that `az` has permission or
credentials to call the service.

## Current directly documented mortgage endpoints

The two Zillow-hosted typed mortgage references currently expose:

- `GET https://mortgageapi.zillow.com/getRates`
- `GET https://mortgageapi.zillow.com/zillowLenderReviews`

Both require an authorized `partnerId`. Do not describe them as anonymous public
APIs merely because the portal says there is no separate auth token.

## Bridge

The Zillow portal sends Agent Reviews, Public Records, and Zestimates to Bridge
Interactive documentation. Bridge is therefore the implementation boundary for
those interfaces, not a detail to hide behind a generic `zillow` request helper.
MLS listing availability additionally depends on participating MLS partners.

The Bridge documentation site is application-like and may not produce a useful
single static HTML response. Preserve its canonical URLs even if a source mirror
captures only the shell page; do not infer missing schemas from old examples.

## Rentals documentation

The Zillow portal currently links three historical `files.hotpads.com` S3 PDF
URLs for its bulk rental feed, real-time listing feed, and lead API guides. Direct
fetches returned HTTP 403 on 2026-09-17. The URLs remain in
`mirror-sources.tsv` with status `linked-blocked` so a broken upstream document
is recorded instead of silently dropped.

The rental listing page also points to MITS. The current MITS material is hosted
by RETTC and has moved beyond the older Zillow-specific feed documentation.
Treat MITS as an external standard/input format, not as evidence that Zillow
accepts every current MITS feature.

## Legacy ZWSID API

Zillow still hosts terms for the older ZWSID-based API family. Those terms name
historical Home Valuation, Property Details, Mortgage, Postings, Reviews, and
Directory APIs, but the present developer portal no longer presents that family
as its normal entry point.

Do not implement old ZWSID calls from archived tutorials unless a current live
Zillow source establishes that the endpoint is still supported.

## Data storage and provenance

For any future command:

- record the source product separately from the geographic/property identifier;
- preserve the exact upstream URL or documented endpoint used;
- record retrieval time for mutable Zillow observations;
- keep credentials, partner IDs when private, OAuth tokens, cookies, and Bridge tokens out of Git;
- do not promote a downloaded CSV, a mocked response, or a documentation fixture into a live API receipt;
- preserve Zillow attribution requirements on Research data in derived exports.

## Transport

The documentation mirror command uses ICU and writes only to the ignored
`.cache/zillow-documentation/` tree. A future live Zillow adapter should also
prefer the repository's small explicit transport style rather than adding a web
framework or browser automation merely to make an endpoint convenient.
