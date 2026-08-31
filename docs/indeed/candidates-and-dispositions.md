# Candidate Sync and Disposition Sync

All operations below use:

```text
POST https://apis.indeed.com/graphql
```

with product-specific OAuth permissions.

## Employer Registration

```text
registerEmployer
manageFeaturesForEmployer
findRegisteredEmployers
deregisterEmployer
```

These register/deregister employers and enable Candidate Sync features.

## Send Candidates

The current Candidate Sync documentation exposes operations including:

```text
registerEmployer
application.initialize
application.submit
findStatuses
application.delete
```

## Retrieve Candidates

Current documented operations include:

```text
registerEmployer
fetchAssets
assetsByTimeRange
stageAssets
```

## Disposition Sync

```text
partnerDisposition.send
```

This sends application status/disposition data for Indeed Apply and non-Indeed-Apply jobs.

Sources:
- https://docs.indeed.com/employer-registration-api/
- https://docs.indeed.com/disposition-sync-api/disposition-sync-api-guide
- https://docs.indeed.com/api/graphql_schema
