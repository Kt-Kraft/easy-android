package easy.camera.model

import androidx.camera.view.PreviewView

public enum class ImplementationMode(internal val value: PreviewView.ImplementationMode) {
  Compatible(PreviewView.ImplementationMode.COMPATIBLE),
  Performance(PreviewView.ImplementationMode.PERFORMANCE);

  public val inverse: ImplementationMode
    get() = when (this) {
      Compatible -> Performance
      else -> Compatible
    }
}
