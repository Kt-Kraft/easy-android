package easy.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import kotlin.reflect.KFunction1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

public interface MVI<UiState, UiAction, SideEffect> {
  public val uiState: StateFlow<UiState>
  public val sideEffect: Flow<SideEffect>
  public fun onAction(uiAction: UiAction)
  public fun updateUiState(block: UiState.() -> UiState)
  public fun updateUiState(newUiState: UiState)
  public fun CoroutineScope.emitSideEffect(effect: SideEffect)
}

@Stable
@Composable
public fun <UiState, UiAction, SideEffect> MVI<UiState, UiAction, SideEffect>.unpack():
  Triple<UiState, KFunction1<UiAction, Unit>, Flow<SideEffect>> =
  Triple(uiState.collectAsState().value, ::onAction, sideEffect)
