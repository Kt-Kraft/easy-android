package feature.homepage.screen.dashboard

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import easy.mvi.unpack

@Composable
public fun HomepageScreenDestination(
  viewModel: DashboardViewModel = viewModel(),
) {
  val (uiState, onAction, sideEffect) = viewModel.unpack()
  DashboardScreen(uiState, sideEffect, onAction)
}
