plugins {
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.convention.compose.app)
  alias(libs.plugins.kotlin.ksp)
}

android {
  namespace = "easy.app"

  defaultConfig {
    applicationId = "easy.app"
    versionCode = 1
    versionName = "0.1.0"
    manifestPlaceholders["appName"] = "Easy Android"
  }
}

kotlin {
  explicitApi()
}

dependencies {
  // App Core
  implementation(projects.core.common)
  implementation(projects.core.designSystem)
  implementation(projects.core.domain)
  implementation(projects.core.navigation)

  // App Features
  implementation(projects.feature.cameraView)

  // App Libs
  implementation(projects.libraries.easyMvi)
  implementation(projects.libraries.easyCamera)

  // Android App Bundle
  implementation(libs.bundles.android.app)

  // Compose
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.androidx.compose)
  implementation(libs.navigation.compose)

  // Koin
  implementation(platform(libs.koin.bom))
  implementation(libs.koin.core)
}
