plugins {
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.convention.compose.lib)
  alias(libs.plugins.convention.publishing)
}

android {
  namespace = "easy.camera"
}

dependencies {
  api(libs.bundles.internal.camerax)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.runtime.livedata)
  implementation(libs.androidx.ui.tooling.preview.android)
  debugImplementation(libs.androidx.ui.tooling)
}
