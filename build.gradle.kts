import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.Detekt

plugins {
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.convention.android.app) apply false
  alias(libs.plugins.convention.android.lib) apply false
  alias(libs.plugins.convention.compose.app) apply false
  alias(libs.plugins.convention.compose.lib) apply false
  alias(libs.plugins.convention.publishing) apply false
  alias(libs.plugins.convention.publish.config) apply false
  alias(libs.plugins.kotlin.ksp) apply false
  alias(libs.plugins.detekt)
  alias(libs.plugins.convention.android.config)
  alias(libs.plugins.ben.manes.versions)
  alias(libs.plugins.version.catalog.update)
  alias(libs.plugins.dokka)
}

convention {
  android {
    targetSdk.set(35)
  }
}

fun isNonStable(version: String): Boolean {
  val stableKeywords = listOf("RELEASE", "FINAL", "GA")
  val regex = Regex("^[0-9,.v-]+(-r)?$")
  return stableKeywords.none { it in version.uppercase() } && !regex.matches(version)
}

tasks.withType<DependencyUpdatesTask> {
  rejectVersionIf {
    isNonStable(candidate.version)
  }
}

versionCatalogUpdate {
  sortByKey.set(true)
  keep {
    keepUnusedVersions.set(true)
    keepUnusedLibraries.set(true)
    keepUnusedPlugins.set(true)
  }
}

dependencies {
  detektPlugins(rootProject.libs.detekt.formatting)
  detektPlugins(rootProject.libs.detekt.rules.libraries)
}

tasks {
  val detektAll by registering(Detekt::class) {
    buildUponDefaultConfig = true
    basePath = rootDir.absolutePath
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    baseline = file("$rootDir/config/detekt/baseline.xml")
    parallel = true

    setSource(files(projectDir))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")

    reports {
      xml.required = true
      html.required = true
      txt.required = true
      sarif.required = true
      md.required = true
    }
  }
}
