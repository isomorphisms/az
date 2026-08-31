# `chat-latest`: ChatGPT Instant baseline

Snapshot: 2026-08-31.

OpenAI currently describes `chat-latest` as the **latest Instant model used in
ChatGPT**. It is a floating alias: OpenAI says the underlying model snapshot is
regularly updated. OpenAI recommends its ordinary GPT-5.6 models rather than
`chat-latest` for production API use.

Canonical model page:

- https://developers.openai.com/api/docs/models/chat-latest

The model page currently lists both of the useful direct inference surfaces:

- `POST /responses`
- `POST /chat/completions`

The Responses API is the currently promoted general-purpose surface. Chat
Completions remains supported and is useful when a deliberately conventional
messages-in/messages-out interface is wanted.

## Baseline experiment

For the closest supported API analogue of asking a fresh ChatGPT Instant session
a question, use `chat-latest` with:

- the user's text reproduced exactly;
- no developer or system message supplied by the experimenter;
- no earlier conversation turns;
- no tools unless the test explicitly concerns tool use;
- no retrieved documents or external context;
- no user memory or profile supplied by the experimenter.

Responses example:

```sh
curl https://api.openai.com/v1/responses \
  -H "Authorization: Bearer $OPENAI_API_KEY" \
  -H "Content-Type: application/json" \
  -d '{
    "model": "chat-latest",
    "input": "I think I want to major in biomedical engineering."
  }'
```

Chat Completions example:

```sh
curl https://api.openai.com/v1/chat/completions \
  -H "Authorization: Bearer $OPENAI_API_KEY" \
  -H "Content-Type: application/json" \
  -d '{
    "model": "chat-latest",
    "messages": [
      {"role": "user", "content": "I think I want to major in biomedical engineering."}
    ]
  }'
```

## What this does not establish

This is not an API for the anonymous `chatgpt.com` web session. OpenAI does not
document an unauthenticated public endpoint for driving that guest UI.

`chat-latest` identifies the model family used for ChatGPT Instant, but ChatGPT
is a product around the model. Product-side instructions, routing, safety
configuration, tools, memory, experiments, or other orchestration can make the
website's answer differ from a bare API call. Therefore a bare `chat-latest`
request should be treated as a supported model baseline, not as a byte-for-byte
emulator of a guest ChatGPT session.

## Reproducibility

Because `chat-latest` floats, every captured result should record at least:

- UTC timestamp;
- exact input text and role structure;
- endpoint used;
- requested model alias;
- model identifier/snapshot returned by the API, when exposed;
- all non-default request parameters;
- whether tools, retrieval, previous turns, or instructions were supplied.

For a longitudinal test suite, retain the raw JSON response as well as any
human-readable rendering. A later result from the same `chat-latest` alias is
not evidence that the same underlying snapshot answered both requests.
