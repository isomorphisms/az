# Search and listings

This is the part of the API most directly relevant to a command-line Reddit
search tool.

## Search endpoints

- `/search`
- `/subreddits/search`
- `/users/search`
- `/api/search_reddit_names`
- `/api/search_subreddits`
- `/api/subreddit_autocomplete`
- `/api/subreddit_autocomplete_v2`

The main content-search detail is documented as:

`GET [/r/subreddit]/search`

Important parameters in the current reference:

- `q` — query text, up to 512 characters.
- `restrict_sr` — restrict the search to the subreddit in the URL.
- `sort` — `relevance`, `hot`, `top`, `new`, or `comments`.
- `t` — `hour`, `day`, `week`, `month`, `year`, or `all`.
- `type` — optional comma-separated result types: `sr`, `link`, `user`.
- `limit` — maximum 100.
- `after` / `before` — listing cursors.
- `count` — number of items already seen.
- `show=all` — disables certain listing filters.
- `sr_detail` — optionally expand subreddit information.

The public search syntax documented by Reddit also recognizes operators such as
`subreddit:`, `author:`, `site:`, `url:`, `selftext:`, `self:yes|no`, and
`nsfw:yes|no`. A CLI can pass those through as query text rather than inventing
its own incompatible search language.

## Generic listing endpoints

- `/best`
- `/by_id/names`
- `/comments/article`
- `/controversial`
- `/duplicates/article`
- `/hot`
- `/new`
- `/rising`
- `/top`
- `/sort`

Many Reddit read endpoints are Listings. Reddit's current reference describes
cursor pagination rather than page numbers: start without `after`, then use the
returned `after` value for the next slice. The common maximum `limit` is 100.

For a small CLI, preserving the Reddit cursor in output is preferable to
pretending the service has stable numbered pages.

## Related read endpoints

- `/api/info`
- `/api/morechildren`
- `/api/saved_categories`
- `/api/v1/scopes`

`/api/info` is useful when the caller already knows fullnames or URLs.
`/api/morechildren` expands collapsed comment trees.

Source:

- https://www.reddit.com/dev/api/
- https://old.reddit.com/wiki/api
