package feature.homepage.screen.dashboard

import androidx.lifecycle.ViewModel
import easy.mvi.MVI
import easy.mvi.mvi

public class DashboardViewModel : ViewModel(),
  MVI<DashboardContract.UiState, DashboardContract.UiAction, DashboardContract.SideEffect> by mvi(
    DashboardContract.UiState(),
  ) {

  override fun onAction(uiAction: DashboardContract.UiAction) {
    when (uiAction) {
      DashboardContract.UiAction.StartService -> {}
    }
  }
}
