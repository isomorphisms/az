# Android search UI

This directory is a phone-facing presentation adapter for `az search`.

Android owns only presentation and platform handoff. Amazon credentials,
Creators API requests, token caching, Internet access, and product-search
semantics remain in the external `bin/az` process.

The Activity:

- uses native Android views only; no WebView, Compose, Kotlin, Gradle, or
  Material Components dependency;
- parses the existing `az search` TSV contract:
  `asin`, `amount`, `currency`, `buy_url`, `title`;
- accepts TSV through `org.isomorphisms.az.SEARCH_RESULTS_TSV` or Android's
  ordinary `ACTION_SEND` text extra;
- accepts the source query through `org.isomorphisms.az.SEARCH_QUERY`;
- does not contain fixture product results;
- requests no Internet permission and contains no Creator credentials;
- opens a result's `buy_url` by handing it to the normal browser.

## Termux handoff

With the APK already installed, run the real search outside Android's app
process and pass only its output into the Activity:

```sh
ysh android-search/show-results.ysh 'K&R C programming'
```

`show-results.ysh` runs:

```text
ysh bin/az search WORDS...
```

and supplies the resulting TSV plus the query to
`org.isomorphisms.az.search/.SearchActivity` through `/system/bin/am`.
The Activity can then filter that already-loaded result set locally. It does not
make an Amazon request itself.

The normal `az` secret file therefore stays in Termux at
`~/.config/az/amazon-secret`; it is neither copied into nor read by the APK.

## Direct build

There is deliberately no Gradle project. With Android SDK platform 36 and
build-tools 36.0.0 installed:

```sh
sh android-search/build-apk.sh
```

The build is the direct platform path:

```text
aapt2 -> javac -> d8 -> zipalign -> apksigner
```

and writes `android-search/build/az-search-debug.apk`. Build-tool and platform
versions can be overridden with `ANDROID_BUILD_TOOLS_VERSION` and
`ANDROID_PLATFORM_VERSION`.

For an attached Android device reachable through `adb`:

```sh
sh android-search/run-device-smoke.sh
```

That builds when necessary, installs the exact APK, force-stops the package,
launches `.SearchActivity` with `am start -W`, and requires both `Status: ok`
and a live package process. This is an install/launch check only; it is not a
claim that a real Creators API search was performed or that result rendering was
visually accepted on the device.
