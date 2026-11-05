# Family Sparkle Chart

A cleaning chart for a three-bedroom house with a yard and shed, two adults and two young kids. It is one self-contained page: open `index.html` in a browser.

## What's in it

**Kids:** big picture tiles for ages 3 and 5, a special job each day, and a voice cheer, chime and confetti for every finished job. Stars build toward a weekly reward and reset on Monday.

**Grown-ups:** daily, weekly, monthly and quarterly checklists. Weekly tasks are split into one area per day, quarterly tasks follow the Australian seasons, and each task can be assigned to either adult.

Use **Names & rewards** to set names, buddies and the reward, and **Edit jobs** to change the job lists.

## Saving

Outside Claude, names, ticks and stars are kept in that browser's local storage, so they stay on one device. When the page runs as a Claude artifact, it saves through Claude's `db` capability instead, which keeps devices in step.

Hosting on GitHub Pages needs no build step: publish the root of the `main` branch.
