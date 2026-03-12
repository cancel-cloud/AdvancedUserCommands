# Repository Guidelines

AdvancedUserCommands is a Fabric Loom Kotlin project targeting Minecraft 1.21.10; use this guide to align contributions.

## Project Structure & Module Organization
- `src/main/kotlin/de/cancelcloud/advancedusercommands/**` contains the gameplay logic: `command/` for Brigadier handlers, `data/` for SNBT formatting, `clipboard/` helpers, etc.
- `src/main/resources` holds `fabric.mod.json`, language assets, and mixin configs; mirror Fabric naming and namespaces when adding content.
- `src/test/kotlin` + `src/test/resources` are dedicated to automation; keep fixtures lightweight and stub Minecraft-only classes.
- Generated artifacts live in `build/`, while dev run configs live in `run/`; neither should be committed.

## Build, Test, and Development Commands
- `./gradlew build` – compile, run tests, and emit the mod JAR under `build/libs/`.
- `./gradlew runClient` – launch a Fabric dev client with the mod for manual verification.
- `./gradlew test` – run JVM tests; add `--info` for verbose logs.
- `./gradlew clean` – drop caches before verifying release builds.

## Coding Style & Naming Conventions
Adopt Kotlin official formatting: four-space indents, trailing commas for multiline literals, and ≤120-character lines. Keep packages under `de.cancelcloud.advancedusercommands`, add one public type per file, and follow PascalCase for types, camelCase for members, and SCREAMING_SNAKE_CASE for constants. Surface text via lang files instead of literals, and use concise KDoc to explain interactions with Fabric or Minecraft APIs.

## Testing Guidelines
Favor Kotlin Test/JUnit5 for pure logic; isolate Fabric dependencies so `./gradlew test` can run headless. Name suites `*Test.kt`, colocate them with the feature they cover, and store SNBT fixtures under `src/test/resources`. When logic touches confirmation flows or clipboard safety limits, capture the scenario in a regression test and document any manual steps executed in `runClient`.

## Commit & Pull Request Guidelines
Commits should mirror existing history (`Add documentation and license`): imperative, descriptive, and scoped to one concern. Squash noisy fixups before review. PRs need a summary of changes, linked issues, test evidence (`./gradlew build`, manual run notes), and screenshots when altering UI text. Confirm CI-equivalent tasks succeed locally and exclude everything from `build/` or user-specific config paths.

## Security & Configuration Tips
Never commit personal `.minecraft` data, API keys, or full logs; trim `latest.log` before sharing. Show configuration tweaks with sanitized snippets referencing `.minecraft/config/advanced-user-commands.json`, and keep optional dependencies (Fabric API, Cloth Config, ModMenu) at the versions declared in `gradle.properties`.
