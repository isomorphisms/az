# State, storage, and hosted-resource endpoints

Canonical reference: https://developers.openai.com/api/reference/overview

## Conversations

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/conversations` | Create persistent conversation state for Responses. |
| GET, POST, DELETE | `/conversations/{conversation_id}` | Retrieve, update, or delete a conversation. |
| GET, POST | `/conversations/{conversation_id}/items` | List or create conversation items. |
| GET, DELETE | `/conversations/{conversation_id}/items/{item_id}` | Retrieve or delete a conversation item. |

## Files

| Method | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/files` | List files or upload a file. |
| GET, DELETE | `/files/{file_id}` | Retrieve file metadata or delete a file. |
| GET | `/files/{file_id}/content` | Download file content. |

## Multipart Uploads

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/uploads` | Begin a multipart upload. |
| POST | `/uploads/{upload_id}/parts` | Add a part to an upload. |
| POST | `/uploads/{upload_id}/complete` | Complete an upload from its parts. |
| POST | `/uploads/{upload_id}/cancel` | Cancel an incomplete upload. |

## Vector Stores

| Method | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/vector_stores` | List or create vector stores. |
| GET, POST, DELETE | `/vector_stores/{vector_store_id}` | Retrieve, update, or delete a vector store. |
| POST | `/vector_stores/{vector_store_id}/search` | Search a vector store. |
| POST | `/vector_stores/{vector_store_id}/file_batches` | Create a file-ingestion batch. |
| GET | `/vector_stores/{vector_store_id}/file_batches/{batch_id}` | Retrieve a file batch. |
| POST | `/vector_stores/{vector_store_id}/file_batches/{batch_id}/cancel` | Cancel a file batch. |
| GET | `/vector_stores/{vector_store_id}/file_batches/{batch_id}/files` | List files in a batch. |
| GET, POST | `/vector_stores/{vector_store_id}/files` | List or add vector-store files. |
| GET, POST, DELETE | `/vector_stores/{vector_store_id}/files/{file_id}` | Retrieve, update, or remove a vector-store file. |
| GET | `/vector_stores/{vector_store_id}/files/{file_id}/content` | Retrieve parsed file content associated with the vector store. |

## Containers

| Method | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/containers` | List or create hosted containers. |
| GET, DELETE | `/containers/{container_id}` | Retrieve or delete a container. |
| GET, POST | `/containers/{container_id}/files` | List or create files in a container. |
| GET, DELETE | `/containers/{container_id}/files/{file_id}` | Retrieve file metadata or delete a container file. |
| GET | `/containers/{container_id}/files/{file_id}/content` | Retrieve container-file content. |

## ChatKit

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/chatkit/sessions` | Create a ChatKit session and client secret. |
| POST | `/chatkit/sessions/{session_id}/cancel` | Cancel a ChatKit session. |
| GET | `/chatkit/threads` | List ChatKit threads. |
| GET, DELETE | `/chatkit/threads/{thread_id}` | Retrieve or delete a ChatKit thread. |
| GET | `/chatkit/threads/{thread_id}/items` | List items in a ChatKit thread. |

ChatKit is a separate hosted application surface; these paths should not be
confused with the deprecated Assistants `/threads/...` resources.

## Skills

| Method | Path | Purpose |
| --- | --- | --- |
| GET, POST | `/skills` | List or create skills. |
| GET, POST, DELETE | `/skills/{skill_id}` | Retrieve, update the default-version pointer for, or delete a skill. |
| GET | `/skills/{skill_id}/content` | Download the current skill bundle. |
| GET, POST | `/skills/{skill_id}/versions` | List versions or create an immutable version. |
| GET, DELETE | `/skills/{skill_id}/versions/{version}` | Retrieve or delete a specific skill version. |
| GET | `/skills/{skill_id}/versions/{version}/content` | Download a specific skill-version bundle. |

A Skill bundle is a hosted resource and is not itself an executable HTTP
endpoint. Only the REST paths above belong in the flat inventory.
