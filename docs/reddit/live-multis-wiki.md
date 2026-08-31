# Live threads, multis, and wiki

## Live threads

- `/api/live/by_id/names`
- `/api/live/create`
- `/api/live/happening_now`
- `/api/live/thread/accept_contributor_invite`
- `/api/live/thread/close_thread`
- `/api/live/thread/delete_update`
- `/api/live/thread/edit`
- `/api/live/thread/hide_discussion`
- `/api/live/thread/invite_contributor`
- `/api/live/thread/leave_contributor`
- `/api/live/thread/report`
- `/api/live/thread/rm_contributor`
- `/api/live/thread/rm_contributor_invite`
- `/api/live/thread/set_contributor_permissions`
- `/api/live/thread/strike_update`
- `/api/live/thread/unhide_discussion`
- `/api/live/thread/update`
- `/live/thread`
- `/live/thread/about`
- `/live/thread/contributors`
- `/live/thread/discussions`
- `/live/thread/updates/update_id`

## Multireddits and filters

- `/api/filter/filterpath`
- `/api/filter/filterpath/r/srname`
- `/api/multi/copy`
- `/api/multi/mine`
- `/api/multi/user/username`
- `/api/multi/multipath`
- `/api/multi/multipath/description`
- `/api/multi/multipath/r/srname`

## Wiki

- `/api/wiki/alloweditor/add`
- `/api/wiki/alloweditor/del`
- `/api/wiki/alloweditor/act`
- `/api/wiki/edit`
- `/api/wiki/hide`
- `/api/wiki/revert`
- `/wiki/discussions/page`
- `/wiki/pages`
- `/wiki/revisions`
- `/wiki/revisions/page`
- `/wiki/settings/page`
- `/wiki/page`

## Notes

These are legitimate parts of the legacy Data API but are peripheral to a
general text-search CLI.

The multireddit paths often support multiple HTTP verbs on the same canonical
path. `endpoints.txt` intentionally records canonical paths, not one line per
method/path pair.

The wiki family includes both reads and moderator/editor mutations. A future
client should separate read commands from edit commands and request only the
OAuth scopes actually needed.

Source: https://www.reddit.com/dev/api/
