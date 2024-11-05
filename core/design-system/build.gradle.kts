plugins {
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.convention.compose.lib)
}

android {
  namespace = "core.designSystem"
}

dependencies {
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.androidx.compose)
}
