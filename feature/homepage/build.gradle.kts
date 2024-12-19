plugins {
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.convention.compose.lib)
}

android {
  namespace = "feature.homepage"
}

dependencies {
  // Project
  implementation(projects.core.common)
  implementation(projects.core.domain)
  implementation(projects.core.navigation)
  implementation(projects.core.designSystem)
  implementation(projects.libraries.easyMvi)
  implementation(projects.libraries.easyPaging)

  // Others
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.androidx.compose)
  implementation(libs.navigation.compose)
  implementation(libs.lifecycle.runtime)
  implementation(libs.lifecycle.viewmodel)
  implementation(libs.lifecycle.viewmodel.compose)
  implementation(libs.lifecycle.runtime.compose)
  implementation(libs.coil)
  implementation(libs.coil.svg)
  implementation(libs.coil.base)
  implementation(libs.coil.compose)
}
