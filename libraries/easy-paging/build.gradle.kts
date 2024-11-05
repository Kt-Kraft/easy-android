plugins {
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.convention.compose.lib)
  alias(libs.plugins.convention.publishing)
}

android {
  namespace = "easy.paging"
}

dependencies {
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.material3)
  implementation(libs.paging.runtime)
  implementation(libs.paging.compose)
}
