# Fix Kotlin Version Incompatibility and Missing Plugin

The project is currently configured with Kotlin 2.2.10, but dependencies are pulling in Kotlin 2.4.0 artifacts. Additionally, the Kotlin Android plugin is not explicitly applied, which can lead to compilation and configuration issues.

## Proposed Changes

### Gradle Configuration

#### [MODIFY] [libs.versions.toml](file:///C:/Users/bened/AndroidStudioProjects/BeastMode_2/gradle/libs.versions.toml)
- Upgrade `kotlin` version to `2.4.10`.
- Add `kotlin-android` plugin definition.
- Add `kotlin-serialization` plugin definition.

#### [MODIFY] [build.gradle.kts (root)](file:///C:/Users/bened/AndroidStudioProjects/BeastMode_2/build.gradle.kts)
- Add `alias(libs.plugins.kotlin.android) apply false`.
- Add `alias(libs.plugins.kotlin.serialization) apply false`.

#### [MODIFY] [app/build.gradle.kts](file:///C:/Users/bened/AndroidStudioProjects/BeastMode_2/app/build.gradle.kts)
- Add `alias(libs.plugins.kotlin.android)`.
- Replace hardcoded serialization plugin with `alias(libs.plugins.kotlin.serialization)`.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:compileDebugKotlin` to ensure the project compiles successfully.
- Run `./gradlew :app:assembleDebug` to verify the full build.

### Manual Verification
- Verify that the IDE no longer shows Kotlin version mismatch errors in `KtorClient.kt` and other files.
