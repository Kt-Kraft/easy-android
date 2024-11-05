plugins {
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.convention.compose.lib)
}

android {
  namespace = "feature.cameraView"
}

dependencies {
  // Core
  implementation(projects.core.common)
  implementation(projects.core.domain)
  implementation(projects.core.navigation)
  implementation(projects.core.designSystem)

  // Libraries
  implementation(projects.libraries.easyCamera)
  implementation(projects.libraries.easyMvi)

  // Compose
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.androidx.compose)
  implementation(libs.navigation.compose)

  // Lifecycle
  implementation(libs.lifecycle.runtime)
  implementation(libs.lifecycle.viewmodel)
  implementation(libs.lifecycle.viewmodel.compose)
  implementation(libs.lifecycle.runtime.compose)

  // Others
  implementation(libs.coil)
  implementation(libs.coil.svg)
  implementation(libs.coil.base)
  implementation(libs.coil.compose)
}
