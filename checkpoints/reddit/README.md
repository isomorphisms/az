# Reddit CLI compiler checkpoints

This directory is a behavior checkpoint for language/compiler work, not a
claim that every implementation is production-ready today.

The same tiny Reddit command is sketched in Ithon, Fieldmouse, and Idriç so a
compiler/runtime change has something concrete to run against.

## Common command contract

```text
reddit url QUERY
reddit fixture FILE
reddit search QUERY
```

`QUERY` is one command-line argument. Quote a multiword query in the shell.
Reddit search operators such as `subreddit:`, `author:`, and `site:` remain part
of the query string; this checkpoint does not invent a second search language.

### `url`

No network. Print the authenticated Data API search URL for `QUERY`:

```text
https://oauth.reddit.com/search?raw_json=1&limit=25&sort=relevance&t=all&q=...
```

This is the smallest useful checkpoint: arguments, strings, and percent
encoding. For the common fixture query `industrial maintenance`, the expected
encoding is `industrial%20maintenance` so every language lane has the same
byte-level target.

### `fixture`

No network. Read a Reddit Listing-shaped JSON file and emit TSV.

The committed synthetic fixture is:

```text
fixture/search.json
```

The required output is:

```text
fixture/expected.tsv
```

Columns are:

```text
created_utc  score  subreddit  author  title  permalink
```

Tabs and newlines inside text fields are flattened to spaces. `permalink` is
expanded to a full `https://www.reddit.com/...` URL.

This checkpoint exercises file input, JSON, arrays/lists, records/dictionaries,
numeric/string conversion, iteration, and deterministic output without making
a network request.

### `search`

Build the same URL, perform one authenticated `GET`, decode the Listing, and
emit exactly the same TSV shape as `fixture`.

The process environment supplies:

```text
REDDIT_ACCESS_TOKEN=...
REDDIT_USER_AGENT=...
```

Secrets and tokens do not belong in this repository.

The request needs both:

```text
Authorization: Bearer TOKEN
User-Agent: USER_AGENT
```

The checkpoint deliberately does not implement Reddit's OAuth token-acquisition
flow yet. A separately acquired access token is enough to exercise the search
path without turning this small fixture into an authentication framework.

## Why ICU is still part of the target

ICU is already the small Idriç HTTP client. Its current public command grammar
supports `GET` and `POST`, but not caller-supplied request headers. Authenticated
Reddit search therefore exposes a useful ICU watchpoint: Reddit needs
`Authorization` and `User-Agent`, so the Idriç lane cannot honestly claim the
network checkpoint until ICU can represent those headers or another explicit
transport boundary is chosen.

Do not silently fall back to undocumented Reddit web endpoints or scraping.

## Language lanes

### Ithon

`ithon/reddit.pi` is the furthest-along executable draft. It uses Ithon's `.pi`
source convention, assignment arrows, mandatory function/result types, and
explicit types around foreign Python-library values. It intentionally stresses
the foreign-library typing boundary around `urllib` and `json`.

Suggested invocation:

```text
ithon ithon/reddit.pi url "industrial maintenance"
ithon ithon/reddit.pi fixture fixture/search.json
ithon ithon/reddit.pi search "industrial maintenance"
```

### Fieldmouse

`fieldmouse/reddit` has no language extension because Fieldmouse's source-file
extension remains deliberately unsettled.

The draft uses the intended small build-script names `arguments`, `environment`,
and `run` instead of pretending Node's `process` object exists. Those exact
calls are watchpoints, not a declaration that their final signatures are
settled. Current Fieldmouse does not yet pass script arguments through its
runner and does not yet have the process/JSON/object surface needed for this
whole program.

That is useful: this is a real-world fixture for the Fieldmouse backlog rather
than another arithmetic demo.

### Idriç

`idric/Reddit.idric` is an explicit draft with named holes at the boundaries
that are not settled yet: percent encoding, file input/JSON Listing decoding,
environment access, and authenticated ICU transport. The command grammar and
result record are written down now so later compiler/library work has a stable
target.

Unresolved holes are intentional failure, not a hidden fallback.

## Manual receipts

`check` is a Grease/YSH-style manual runner. It is intentionally not wired into
the root `make test` yet because Fieldmouse and Idriç have known unfinished
boundaries.

Run it as:

```text
ysh check
```

or point it at explicit compiler/runtime builds:

```text
ITHON=/opt/ithon/ithon \
FIELDMOUSE=/opt/fieldmouse/build/exec/fieldmouse \
IDRIC=/opt/Idric/build/exec/idris2 \
ysh check
```

It emits only the three ordinary receipt states:

```text
PASS    checkpoint
FAIL    checkpoint
SKIP    checkpoint
```

For Ithon it also requests an `ITHON_CHECK_RECEIPT`, so a successful language
check has the source/lowered-source hash receipt supplied by Ithon itself.

## Checkpoint ladder

A lane can advance independently through these checkpoints:

1. source parses/checks;
2. `url` receives a command argument;
3. `url` percent-encodes it correctly;
4. `fixture` reads the synthetic file;
5. `fixture` decodes the Listing and matches `expected.tsv` byte-for-byte;
6. `search` reads token and user-agent from the environment;
7. `search` performs the authenticated request;
8. live Listing output uses the same TSV contract as the fixture.

A compiler or runtime regression should therefore fail at a named boundary
instead of merely producing "Reddit tool broken".
