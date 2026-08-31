# Applications, Hiring Signals, RDB, and feeds

## Hiring Signals

Base URL documented by ZipRecruiter:

```text
https://api.ziprecruiter.com/hiring-signal/v0
```

Event submission route:

```text
POST https://api.ziprecruiter.com/hiring-signal/v0/event
```

Authentication: HTTP Basic. Hiring Signals records events in a job application's history. Application payloads delivered by ZipRecruiter include `zr_application_id`, which can be used to correlate later signals.

## Apply Webhook

There is **no universal ZipRecruiter callback URL to inventory**. ZipRecruiter POSTs application JSON to one HTTPS endpoint supplied by the partner. Optional HMAC signatures can authenticate deliveries.

## Resume Database (RDB) Integration

Likewise, ZipRecruiter POSTs sourced candidates to a partner-supplied HTTPS endpoint; no fixed ZipRecruiter request path exists. It can generally reuse the Apply Webhook endpoint.

## XML Feed Import

For bulk job import ZipRecruiter pulls a partner-hosted HTTP(S) or FTP XML feed. The feed URL belongs to the partner, so no fake `api.ziprecruiter.com` endpoint is listed for it.

Sources:
- https://www.ziprecruiter.com/partner/documentation/api/hiring-signals-api/hiring-signals-api/
- https://www.ziprecruiter.com/partner/documentation/apply-webhook/
- https://www.ziprecruiter.com/partner/documentation/rdb-integration/
- https://www.ziprecruiter.com/partner/documentation/
