package easy.camera.extensions

import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

internal fun CameraManager.isImageAnalysisSupported(lensFacing: Int?): Boolean {
  val cameraId = findCameraIdByFacing(lensFacing) ?: return false
  return isHardwareLevelSupported(cameraId, CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_3)
}

private fun CameraManager.findCameraIdByFacing(lensFacing: Int?): String? {
  return cameraIdList.firstOrNull {
    getCameraCharacteristics(it).get(CameraCharacteristics.LENS_FACING) == lensFacing
  }
}

private fun CameraManager.isHardwareLevelSupported(cameraId: String, requiredLevel: Int): Boolean {
  val level = getCameraCharacteristics(cameraId)
    .get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL) ?: 0
  return level >= requiredLevel
}
