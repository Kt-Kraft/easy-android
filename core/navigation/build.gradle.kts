plugins {
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.convention.compose.lib)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "core.navigation"
}

dependencies {
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.androidx.compose)
  implementation(libs.navigation.compose)
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.retrofit.converter.kotlinx.serialization)
}
