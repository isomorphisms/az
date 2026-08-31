# Reuters API

Reuters publicly describes its content-delivery API as a GraphQL integration
that returns JSON and supports filtering/search/retrieval of licensed Reuters
content. The public product material does not publish the customer GraphQL
endpoint or schema.

Therefore `endpoints.txt` deliberately records:

```text
POST ${REUTERS_GRAPHQL_URL}
```

Do not guess a Reuters endpoint or copy an unofficial schema merely to make this
checkpoint look complete. A licensed customer can provide the endpoint through:

```text
REUTERS_GRAPHQL_URL=...
```

and a query document through a file. Authentication headers are likewise part
of the licensed integration and are an ICU custom-request-header watchpoint.

The useful compiler/runtime surface here is still substantial before a live
subscription exists: argv, environment, file input, exact GraphQL request-body
construction, JSON escaping, process execution, and explicit SKIP at the
transport/auth boundary.
