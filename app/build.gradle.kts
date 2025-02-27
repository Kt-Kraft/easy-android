plugins {
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.convention.compose.app)
}

android {
  namespace = "easy.app"

  defaultConfig {
    applicationId = "easy.app"
    versionCode = 1
    versionName = "0.1.0"
  }

  flavorDimensions += "environment"

  productFlavors {
    create("development") {
      dimension = "environment"
    }
    create("production") {
      dimension = "environment"
    }
  }
}

kotlin {
  explicitApi()
}

dependencies {
  // Project
  implementation(projects.core.common)
  implementation(projects.core.designSystem)
  implementation(projects.core.domain)
  implementation(projects.core.navigation)
  implementation(projects.feature.homepage)
  implementation(projects.libraries.easyMvi)
  implementation(projects.libraries.easyPaging)

  // Others
  implementation(libs.bundles.android.app)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.androidx.compose)
  implementation(libs.navigation.compose)
  implementation(platform(libs.koin.bom))
  implementation(libs.koin.core)
}
