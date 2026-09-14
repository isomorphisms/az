# Anna's Archive

The `AA` branch is intentionally narrow. It uses Anna's Archive's stable member
JSON endpoint to resolve an MD5 to a fast-download URL:

```text
/dyn/api/fast_download.json?md5=...&key=...
```

There is no HTML search scraper in this branch. Search/indexing is a separate
problem and should use Anna's published metadata rather than making this tool
depend on the website's current HTML.

## Command

```sh
AA=... ysh bin/aa resolve 6722faecdb9370ad0d2e447cce370950
```

The command prints only the returned download URL. `jq` handles JSON and URI
encoding. ICU is the only HTTP transport; the command does not fall back to
`curl`, Python HTTP libraries, or Node.

The default API host is `https://annas-archive.gl`. Because the membership key
must be sent in the upstream query string, the command refuses to send it to an
arbitrary host. The currently accepted mirrors are `.gl`, `.pk`, and `.gd`.

## GitHub Environment

The repository has a GitHub Environment named `AA`, with an Environment secret
also named `AA`. `.github/workflows/aa-live.yml` selects that environment and
maps `${{ secrets.AA }}` to the process environment variable `AA` only for the
resolver step.

The live workflow is manual because it uses a real membership credential and a
real upstream service. It runs on GitHub-hosted Ubuntu 24.04, builds the pinned
Idric, Idric-Net, and ICU revisions used by the earlier hosted AA probe, then
runs the resolver through that compiled ICU binary. The hosted image does not
need a self-hosted Debian runner. The resolved URL is written to runner temporary
storage and its shape is checked without printing the URL to the Actions log.

The upstream API requires the key in the request URL. Consequently the ICU
process argument list briefly contains the key while the request runs. The
wrapper does not print or persist that request URL.
