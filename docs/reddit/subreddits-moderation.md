# Subreddits, moderation, modmail, and mod notes

## Subreddit discovery, metadata, subscriptions, and configuration

- `/about/banned`
- `/about/contributors`
- `/about/moderators`
- `/about/muted`
- `/about/wikibanned`
- `/about/wikicontributors`
- `/about/where`
- `/api/delete_sr_banner`
- `/api/delete_sr_header`
- `/api/delete_sr_icon`
- `/api/delete_sr_img`
- `/api/recommend/sr/srnames`
- `/api/search_reddit_names`
- `/api/search_subreddits`
- `/api/site_admin`
- `/api/submit_text`
- `/api/subreddit_autocomplete`
- `/api/subreddit_autocomplete_v2`
- `/api/subreddit_stylesheet`
- `/api/subscribe`
- `/api/upload_sr_img`
- `/api/v1/subreddit/post_requirements`
- `/r/subreddit/about`
- `/r/subreddit/about/edit`
- `/r/subreddit/about/rules`
- `/r/subreddit/about/traffic`
- `/sidebar`
- `/sticky`
- `/subreddits/default`
- `/subreddits/gold`
- `/subreddits/mine/contributor`
- `/subreddits/mine/moderator`
- `/subreddits/mine/streams`
- `/subreddits/mine/subscriber`
- `/subreddits/mine/where`
- `/subreddits/new`
- `/subreddits/popular`
- `/subreddits/search`
- `/subreddits/where`
- `/users/new`
- `/users/popular`
- `/users/search`
- `/users/where`

## Moderation queues and actions

- `/about/edited`
- `/about/log`
- `/about/modqueue`
- `/about/reports`
- `/about/spam`
- `/about/unmoderated`
- `/about/location`
- `/api/accept_moderator_invite`
- `/api/approve`
- `/api/distinguish`
- `/api/ignore_reports`
- `/api/leavecontributor`
- `/api/leavemoderator`
- `/api/remove`
- `/api/show_comment`
- `/api/snooze_reports`
- `/api/unignore_reports`
- `/api/unsnooze_reports`
- `/api/update_crowd_control_level`
- `/stylesheet`

## New modmail

- `/api/mod/bulk_read`
- `/api/mod/conversations`
- `/api/mod/conversations/:conversation_id`
- `/api/mod/conversations/:conversation_id/approve`
- `/api/mod/conversations/:conversation_id/archive`
- `/api/mod/conversations/:conversation_id/disapprove`
- `/api/mod/conversations/:conversation_id/highlight`
- `/api/mod/conversations/:conversation_id/mute`
- `/api/mod/conversations/:conversation_id/temp_ban`
- `/api/mod/conversations/:conversation_id/unarchive`
- `/api/mod/conversations/:conversation_id/unban`
- `/api/mod/conversations/:conversation_id/unmute`
- `/api/mod/conversations/read`
- `/api/mod/conversations/subreddits`
- `/api/mod/conversations/unread`
- `/api/mod/conversations/unread/count`

## Moderator notes

- `/api/mod/notes`
- `/api/mod/notes/recent`

## Notes

A large fraction of this file is privileged moderator surface. The endpoint
inventory records it because it is part of the current reference, but a
read-only search client should not request moderation scopes simply because the
routes exist.

`/api/recommend/sr/srnames` is explicitly marked deprecated in the current
reference.

Several compact-index paths are really subreddit-scoped families. For example
the detailed documentation shows forms such as
`[/r/subreddit]/about/where` and `[/r/subreddit]/api/friend`.

Source: https://www.reddit.com/dev/api/
