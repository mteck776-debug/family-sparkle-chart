# Family Sparkle Chart

A cleaning chart for a three-bedroom house with a yard and shed, two adults and two young kids. It is one self-contained page: open `index.html` in a browser.

## What's in it

**Kids:** big picture tiles for ages 3 and 5, a special job each day, and a voice cheer, chime and confetti for every finished job. Stars build toward a weekly reward and reset on Monday.

**Grown-ups:** daily, weekly, monthly and quarterly checklists. Weekly tasks are split into one area per day, quarterly tasks follow the Australian seasons, and each task can be assigned to either adult.

Use **Names & rewards** to set names, buddies and the reward, and **Edit jobs** to change the job lists.

## Saving to GitHub

The chart saves to `chart.json` on this repo's `data` branch, so everyday ticks stay out of the code history on `main`. Every device reads and writes that file, and changes made on two devices at once are merged.

Each device connects once with a fine-grained access token:

1. On GitHub, go to Settings, Developer settings, then Fine-grained tokens, and generate a new token.
2. Under repository access, choose only `family-sparkle-chart`.
3. Under permissions, set Contents to Read and write.
4. In the chart, tap **GitHub** and paste the token.

The token stays on that device and is never written to the repo. Offline changes wait on the device and save when it reconnects.

**The repo must be private.** `chart.json` holds your family's names, so the chart will not save while the repo is public.

## Android app

The `android` folder is a small Kotlin app that shows this same `index.html` and uses the phone's voice for the cheers. Open that folder in Android Studio, or build it with `./gradlew assembleRelease`. The build copies in the latest `index.html` automatically.
