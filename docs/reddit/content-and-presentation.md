# Content, flair, emoji, widgets, and captcha

## Posts and comments

- `/api/comment`
- `/api/del`
- `/api/editusertext`
- `/api/follow_post`
- `/api/hide`
- `/api/info`
- `/api/lock`
- `/api/marknsfw`
- `/api/morechildren`
- `/api/report`
- `/api/save`
- `/api/saved_categories`
- `/api/sendreplies`
- `/api/set_contest_mode`
- `/api/set_subreddit_sticky`
- `/api/set_suggested_sort`
- `/api/spoiler`
- `/api/store_visits`
- `/api/submit`
- `/api/unhide`
- `/api/unlock`
- `/api/unmarknsfw`
- `/api/unsave`
- `/api/unspoiler`
- `/api/vote`

## Flair

- `/api/clearflairtemplates`
- `/api/deleteflair`
- `/api/deleteflairtemplate`
- `/api/flair`
- `/api/flair_template_order`
- `/api/flairconfig`
- `/api/flaircsv`
- `/api/flairlist`
- `/api/flairselector`
- `/api/flairtemplate`
- `/api/flairtemplate_v2`
- `/api/link_flair`
- `/api/link_flair_v2`
- `/api/selectflair`
- `/api/setflairenabled`
- `/api/user_flair`
- `/api/user_flair_v2`

## Emoji

- `/api/v1/subreddit/emoji.json`
- `/api/v1/subreddit/emoji/emoji_name`
- `/api/v1/subreddit/emoji_asset_upload_s3.json`
- `/api/v1/subreddit/emoji_custom_size`
- `/api/v1/subreddit/emojis/all`

## Widgets

- `/api/widget`
- `/api/widget/widget_id`
- `/api/widget_image_upload_s3`
- `/api/widget_order/section`
- `/api/widgets`

## Captcha

- `/api/needs_captcha`

## Notes

The posts/comments family contains both read helpers and destructive/write
operations: submit, edit, delete, vote, save, hide, report, lock, NSFW/spoiler
state, sticky state, contest mode, and suggested sort. These should not be
bundled into the first search client merely because they share an API family.

For a search-oriented first implementation, the useful members are mostly
`/api/info`, comment listings, and possibly `/api/morechildren`; write scopes
can stay absent.

Flair, emoji, and widgets are primarily subreddit presentation/configuration
surfaces and frequently require moderator privileges.

Source: https://www.reddit.com/dev/api/
