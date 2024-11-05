plugins {
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.convention.compose.lib)
  alias(libs.plugins.convention.publishing)
}

android {
  namespace = "easy.mvi"
}

dependencies {
  implementation(libs.lifecycle.common)
  implementation(libs.lifecycle.viewmodel)
  implementation(libs.lifecycle.runtime.compose)
  implementation(libs.kotlinx.coroutines.android)
}
