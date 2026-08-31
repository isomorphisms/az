# Accounts, users, messages, and announcements

## Logged-in account and preferences

- `/api/v1/me`
- `/api/v1/me/blocked`
- `/api/v1/me/friends`
- `/api/v1/me/karma`
- `/api/v1/me/prefs`
- `/api/v1/me/trophies`
- `/prefs/blocked`
- `/prefs/friends`
- `/prefs/messaging`
- `/prefs/trusted`
- `/prefs/where`

## Users and user relationships/history

- `/api/block_user`
- `/api/friend`
- `/api/report_user`
- `/api/setpermissions`
- `/api/unfriend`
- `/api/user_data_by_account_ids`
- `/api/username_available`
- `/api/v1/me/friends/username`
- `/api/v1/user/username/trophies`
- `/user/username/about`
- `/user/username/comments`
- `/user/username/downvoted`
- `/user/username/gilded`
- `/user/username/hidden`
- `/user/username/overview`
- `/user/username/saved`
- `/user/username/submitted`
- `/user/username/upvoted`
- `/user/username/where`

## Private messages

- `/api/compose`
- `/api/del_msg`
- `/api/read_all_messages`
- `/api/read_message`
- `/message/inbox`
- `/message/sent`
- `/message/unread`
- `/message/where`

## Announcements

- `/api/announcements/v1`
- `/api/announcements/v1/hide`
- `/api/announcements/v1/read`
- `/api/announcements/v1/read_all`
- `/api/announcements/v1/unread`

## Notes

The account endpoints mix read-only identity/preferences with preference
mutation. User-history routes such as `saved`, `upvoted`, `downvoted`, and
`hidden` are account-sensitive and should be treated as authenticated/private
data, not as ordinary public profile reads.

`/api/friend`, `/api/unfriend`, and related relationship endpoints are broad
legacy primitives used for moderator/contributor/ban/mute relationships as well
as older "friend" behavior. A search-only CLI has no reason to request those
write scopes.

The message endpoints are also outside the minimal scope of a search client and
should remain unrequested unless a separate messaging command is deliberately
added.

Source: https://www.reddit.com/dev/api/
