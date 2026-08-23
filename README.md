# az

Small Grease command-line tools for product lookup and price observations.

`az` deliberately does not reproduce Amazon's web application. It asks for a
small amount of structured data, reduces it to a price observation, and leaves
the long-lived history in ordinary local files that IB or other programs can
index later.

The script is Grease/YSH-compatible shell and currently uses the `ysh` entry
point supplied by the Grease/Oils tree.

## Today

```sh
# Public affiliate link, no API credentials required.
ysh bin/az link B012345678

# Record something you saw yourself.
ysh bin/az observe B012345678 19.99

# Once Creators credentials are configured:
ysh bin/az price B012345678
ysh bin/az search 'K&R C programming'
ysh bin/az history B012345678
```

`price` uses Amazon Creators API `GetItems` with `OffersV2` and appends one row
to the price ledger. `search` uses `SearchItems` and prints results without
silently filling the ledger with every search result.

The local ledger is append-only TSV:

```text
observed_at  source  method  product  amount  currency  buy_url
```

By default it lives at `~/.local/state/az/prices.tsv`, following
`XDG_STATE_HOME` when set. The same ledger can store observations from other
merchants with `az record`.

## Amazon configuration

The US marketplace and public Associates tag are checked in at
`config/amazon-public`:

```text
AZ_MARKETPLACE=www.amazon.com
AZ_PARTNER_TAG=macguyver03-20
```

Environment variables can override those defaults, so a fork or another
installation can use another tag or no tagged distribution.

Creators credentials are secrets and are never committed. Copy the example:

```sh
mkdir -p ~/.config/az
cp config/amazon-secret.example ~/.config/az/amazon-secret
chmod 600 ~/.config/az/amazon-secret
$EDITOR ~/.config/az/amazon-secret
```

It expects:

```text
AZ_AMAZON_CREDENTIAL_ID=...
AZ_AMAZON_CREDENTIAL_SECRET=...
AZ_AMAZON_CREDENTIAL_VERSION=3.1
```

Credential versions 3.1, 3.2, and 3.3 select Amazon's North America, Europe,
and Far East Login-with-Amazon token endpoints respectively. Access tokens are
cached locally until shortly before their one-hour expiry instead of requesting
a new token for every price lookup.

Dependencies are intentionally boring: Grease/YSH, `curl`, `jq`, `grep`,
`sed`, `awk`, and `date`.

```sh
ysh bin/az doctor
make test
sudo make install
```

## Product identity

An ASIN is a useful merchant key, not the universal identity of a thing. The
price ledger therefore stores `source` and `product` separately. `az record`
can already write something like `isbn:...` or another application's canonical
product ID. This keeps Amazon as one replaceable source for prices rather than
making the rest of the system an Amazon database.

## Associates disclosure

This distribution can generate Amazon links containing the Associates tag
`macguyver03-20`. As an Amazon Associate, the operator of that tag may earn from
qualifying purchases.
