package feature.homepage.screen.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow

@Composable
public fun DashboardScreen(
  uiState: DashboardContract.UiState,
  sideEffect: Flow<DashboardContract.SideEffect>,
  onAction: (DashboardContract.UiAction) -> Unit,
) {
  val snackBarHostState = remember { SnackbarHostState() }

  Scaffold(
    snackbarHost = { SnackbarHost(snackBarHostState) },
  ) { paddingValues ->
    Box(
      modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize(),
    ) {

    }
  }
}

