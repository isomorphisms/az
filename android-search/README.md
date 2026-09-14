# Android search UI stub

This directory is a phone-facing presentation stub for `az search`.

It borrows the narrow native-view pattern from IB's `android-prepaint`: Android
owns only presentation and platform handoff. Amazon credentials, Creators API
requests, token caching, and product-search semantics remain in `bin/az`.

The first slice is intentionally small:

- native Android views only; no WebView, Compose, Kotlin, or Material Components
  dependency;
- Material-3-like dark surfaces, large rounded search field, tonal cards, and
  touch-sized controls;
- parses the existing `az search` TSV contract:
  `asin`, `amount`, `currency`, `buy_url`, `title`;
- loads `sample-search.tsv` so the UI can be exercised before transport wiring;
- accepts shared/plain TSV text and the explicit
  `org.isomorphisms.az.SEARCH_RESULTS_TSV` intent extra for later Termux/Grease
  handoff;
- opens a result's `buy_url` in the normal browser;
- requests no Internet permission and contains no Creator credentials.

The search button currently filters the fixture locally and labels that state
explicitly. It does **not** claim a live Creators API search. The next wiring
step should execute the existing `az search` path outside the APK and deliver
its TSV output across the narrow intent/transport boundary rather than moving
secrets or Amazon protocol logic into this Activity.

There is deliberately no Gradle project here. This is a source-level UI stub,
not a build, install, launch, or device acceptance receipt.
