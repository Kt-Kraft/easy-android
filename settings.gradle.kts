rootProject.name = "easy-android"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
  repositories {
    mavenLocal()
    maven(url = "https://maven.pkg.github.com/Kt-Kraft/build-logic/") {
      credentials {
        username = System.getenv("GITHUB_USERNAME")
        password = System.getenv("GITHUB_TOKEN")
      }
    }
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    maven(url = "https://jitpack.io")
    maven(url = "https://androidx.dev/storage/compose-compiler/repository/")
    google()
    mavenCentral()
  }
}

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

include(":app")

include(":core:common")
include(":core:domain")
include(":core:design-system")
include(":core:navigation")

include(":feature:homepage")
include(":libraries:easy-paging")
include(":libraries:easy-mvi")
