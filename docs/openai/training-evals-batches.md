# Evals, fine-tuning, batches, and model metadata

Canonical reference: https://developers.openai.com/api/reference/overview

## Evals

| Method | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/evals` | List or create eval definitions. |
| GET, POST, DELETE | `/evals/{eval_id}` | Retrieve, update, or delete an eval. |
| GET, POST | `/evals/{eval_id}/runs` | List runs or start an eval run. |
| GET, POST, DELETE | `/evals/{eval_id}/runs/{run_id}` | Retrieve, cancel, or delete a run. The POST operation is the cancellation operation in the pinned spec. |
| GET | `/evals/{eval_id}/runs/{run_id}/output_items` | List output items produced by a run. |
| GET | `/evals/{eval_id}/runs/{run_id}/output_items/{output_item_id}` | Retrieve a single output item. |

## Fine-tuning: graders

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/fine_tuning/alpha/graders/run` | Run an alpha grader against supplied data. |
| POST | `/fine_tuning/alpha/graders/validate` | Validate a grader definition. |

## Fine-tuning: checkpoint permissions

| Method | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/fine_tuning/checkpoints/{fine_tuned_model_checkpoint}/permissions` | List or create project permissions for a fine-tuned checkpoint. |
| DELETE | `/fine_tuning/checkpoints/{fine_tuned_model_checkpoint}/permissions/{permission_id}` | Delete a checkpoint permission. |

The current human reference presents retrieval/listing under the permissions
resource. The pinned OpenAPI snapshot models the item-specific path above as a
DELETE operation; the collection path carries listing/creation.

## Fine-tuning jobs

| Method | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/fine_tuning/jobs` | List or create fine-tuning jobs. |
| GET | `/fine_tuning/jobs/{fine_tuning_job_id}` | Retrieve a fine-tuning job. |
| POST | `/fine_tuning/jobs/{fine_tuning_job_id}/cancel` | Cancel a job. |
| POST | `/fine_tuning/jobs/{fine_tuning_job_id}/pause` | Pause a job. |
| POST | `/fine_tuning/jobs/{fine_tuning_job_id}/resume` | Resume a paused job. |
| GET | `/fine_tuning/jobs/{fine_tuning_job_id}/events` | List job events. |
| GET | `/fine_tuning/jobs/{fine_tuning_job_id}/checkpoints` | List generated checkpoints. |

## Batch API

| Method | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/batches` | List batches or create a batch from an uploaded request file. |
| GET | `/batches/{batch_id}` | Retrieve batch state/results metadata. |
| POST | `/batches/{batch_id}/cancel` | Cancel a batch. |

The endpoint strings embedded inside a batch input file (for example
`/v1/responses`) are references to other API operations and are not additional
Batch REST resources.

## Models

| Method | Path | Purpose |
| --- | --- | --- |
| GET | `/models` | List models visible to the credential. |
| GET, DELETE | `/models/{model}` | Retrieve model metadata or delete an eligible fine-tuned model. |

Model IDs and aliases such as `chat-latest` are not endpoints. They are values
passed to model-capable endpoints. See `chat-latest.md` for the ChatGPT-Instant
baseline use case.
