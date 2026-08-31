# Administration endpoints

Canonical reference: https://developers.openai.com/api/reference/overview

Administration routes require an OpenAI Admin API key rather than an ordinary
project API key. See `authentication.md`.

## Admin API keys and audit logs

| Methods | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/organization/admin_api_keys` | List or create organization Admin API keys. |
| GET, DELETE | `/organization/admin_api_keys/{key_id}` | Retrieve or revoke an Admin API key. |
| GET | `/organization/audit_logs` | List organization audit-log records. |

## Certificates and data retention

| Methods | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/organization/certificates` | List or create organization certificates. |
| GET, POST, DELETE | `/organization/certificates/{certificate_id}` | Retrieve, update, or delete a certificate. |
| POST | `/organization/certificates/activate` | Activate certificates for the organization. |
| POST | `/organization/certificates/deactivate` | Deactivate certificates for the organization. |
| GET, POST | `/organization/data_retention` | Retrieve or update organization data-retention controls. |

## Organization groups

| Methods | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/organization/groups` | List or create groups. |
| GET, POST, DELETE | `/organization/groups/{group_id}` | Retrieve, update, or delete a group. |
| GET, POST | `/organization/groups/{group_id}/roles` | List or assign roles to a group. |
| GET, DELETE | `/organization/groups/{group_id}/roles/{role_id}` | Retrieve or remove a group role assignment. |
| GET, POST | `/organization/groups/{group_id}/users` | List users in a group or add a user. |
| GET, DELETE | `/organization/groups/{group_id}/users/{user_id}` | Retrieve or remove a group membership. |

## Invitations

| Methods | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/organization/invites` | List or create invitations. |
| GET, DELETE | `/organization/invites/{invite_id}` | Retrieve or delete an invitation. |

## Projects

| Methods | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/organization/projects` | List or create projects. |
| GET, POST | `/organization/projects/{project_id}` | Retrieve or update a project. |
| POST | `/organization/projects/{project_id}/archive` | Archive a project. |

OpenAI models project deletion as archival rather than a DELETE operation on the
project resource.

### Project API keys

| Methods | Path | Purpose |
| --- | --- | --- |
| GET | `/organization/projects/{project_id}/api_keys` | List project API keys. |
| GET, DELETE | `/organization/projects/{project_id}/api_keys/{api_key_id}` | Retrieve or revoke a project API key. |

### Project certificates and retention

| Methods | Path | Purpose |
| --- | --- | --- |
| GET | `/organization/projects/{project_id}/certificates` | List certificates assigned to a project. |
| POST | `/organization/projects/{project_id}/certificates/activate` | Activate certificates for a project. |
| POST | `/organization/projects/{project_id}/certificates/deactivate` | Deactivate certificates for a project. |
| GET, POST | `/organization/projects/{project_id}/data_retention` | Retrieve or update project data-retention controls. |

### Project groups and their role assignments

| Methods | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/organization/projects/{project_id}/groups` | List project groups or add a group. |
| GET, DELETE | `/organization/projects/{project_id}/groups/{group_id}` | Retrieve or remove a project-group association. |
| GET, POST | `/projects/{project_id}/groups/{group_id}/roles` | List or assign roles to a project group. |
| GET, DELETE | `/projects/{project_id}/groups/{group_id}/roles/{role_id}` | Retrieve or remove a project-group role assignment. |

The role-assignment paths above intentionally begin with `/projects`, not
`/organization/projects`. That is how the pinned OpenAPI specification publishes
them. `endpoints.txt` preserves this rather than correcting it speculatively.

### Project permissions and rate limits

| Methods | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/organization/projects/{project_id}/hosted_tool_permissions` | Retrieve or update hosted-tool permissions. |
| GET, POST, DELETE | `/organization/projects/{project_id}/model_permissions` | Retrieve, update, or delete the project's model-permission policy. |
| GET | `/organization/projects/{project_id}/rate_limits` | List project rate limits. |
| POST | `/organization/projects/{project_id}/rate_limits/{rate_limit_id}` | Update a project rate limit. |

### Project roles

| Methods | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/projects/{project_id}/roles` | List or create project roles. |
| GET, POST, DELETE | `/projects/{project_id}/roles/{role_id}` | Retrieve, update, or delete a project role. |

These paths also use the spec's top-level `/projects` prefix.

### Service accounts

| Methods | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/organization/projects/{project_id}/service_accounts` | List or create project service accounts. |
| GET, POST, DELETE | `/organization/projects/{project_id}/service_accounts/{service_account_id}` | Retrieve, update, or delete a service account. |
| POST | `/organization/projects/{project_id}/service_accounts/{service_account_id}/api_keys` | Create an API key for a service account. |

### Project spending controls

| Methods | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/organization/projects/{project_id}/spend_alerts` | List or create project spend alerts. |
| GET, POST, DELETE | `/organization/projects/{project_id}/spend_alerts/{alert_id}` | Retrieve, update, or delete a project spend alert. |
| GET, POST, DELETE | `/organization/projects/{project_id}/spend_limit` | Retrieve, create/replace, or delete the project's hard spend limit. |

### Project users and role assignments

| Methods | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/organization/projects/{project_id}/users` | List project users or add a user. |
| GET, POST, DELETE | `/organization/projects/{project_id}/users/{user_id}` | Retrieve, update, or remove a project user. |
| GET, POST | `/projects/{project_id}/users/{user_id}/roles` | List or assign roles to a project user. |
| GET, DELETE | `/projects/{project_id}/users/{user_id}/roles/{role_id}` | Retrieve or remove a project-user role assignment. |

Again, the role-assignment paths use `/projects` in the pinned specification.

## Organization roles

| Methods | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/organization/roles` | List or create organization roles. |
| GET, POST, DELETE | `/organization/roles/{role_id}` | Retrieve, update, or delete an organization role. |

## Organization spending controls

| Methods | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/organization/spend_alerts` | List or create organization spend alerts. |
| GET, POST, DELETE | `/organization/spend_alerts/{alert_id}` | Retrieve, update, or delete an organization spend alert. |
| GET, POST, DELETE | `/organization/spend_limit` | Retrieve, create/replace, or delete the organization hard spend limit. |

## Organization usage and costs

All routes in this section are GET operations.

| Path | Metric family |
| --- | --- |
| `/organization/costs` | Costs |
| `/organization/usage/audio_speeches` | Speech generation |
| `/organization/usage/audio_transcriptions` | Audio transcription |
| `/organization/usage/code_interpreter_sessions` | Code Interpreter sessions |
| `/organization/usage/completions` | Completion/response token usage |
| `/organization/usage/embeddings` | Embeddings |
| `/organization/usage/file_search_calls` | File-search calls |
| `/organization/usage/images` | Image generation/edit usage |
| `/organization/usage/moderations` | Moderation usage |
| `/organization/usage/vector_stores` | Vector-store usage |
| `/organization/usage/web_search_calls` | Web-search calls |

## Organization users and their roles

| Methods | Path | Purpose |
| --- | --- | --- |
| GET | `/organization/users` | List organization users. |
| GET, POST, DELETE | `/organization/users/{user_id}` | Retrieve, update, or remove an organization user. |
| GET, POST | `/organization/users/{user_id}/roles` | List or assign organization roles to a user. |
| GET, DELETE | `/organization/users/{user_id}/roles/{role_id}` | Retrieve or remove a user-role assignment. |

The top-level Users API does not expose a separate create-user operation in the
current human reference; invitations and membership-management routes are the
relevant creation/addition surfaces.
