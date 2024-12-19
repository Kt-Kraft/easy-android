package feature.homepage.screen.dashboard

public interface DashboardContract {

  public data class UiState(
    public val isLoading: Boolean = false,
  )

  public sealed interface UiAction {
    public data object StartService : UiAction
  }

  public sealed interface SideEffect {
    
    public data class ShowSnackBar(
      val message: Int
    ) : SideEffect
  }
}
