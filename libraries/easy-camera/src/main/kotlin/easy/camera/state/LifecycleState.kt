package easy.camera.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

/**
 * Observe lifecycle as state composable.
 * Based on https://stackoverflow.com/a/69061897/11903815.
 * */
@Composable
internal fun Lifecycle.observeAsState(): State<Lifecycle.Event> {
  val lifecycleEventState = remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
  DisposableEffect(this) {
    val observer = LifecycleEventObserver { _, event ->
      lifecycleEventState.value = event
    }
    this@observeAsState.addObserver(observer)
    onDispose {
      this@observeAsState.removeObserver(observer)
    }
  }
  return lifecycleEventState
}
