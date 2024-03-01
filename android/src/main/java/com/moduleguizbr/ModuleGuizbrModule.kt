package com.moduleguizbr

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import java.io.IOException

class ModuleGuizbrModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  companion object {
    const val NAME = "ModuleGuizbr"
    private const val REQUEST_CAMERA_PERMISSION = 1
    private const val REQUEST_IMAGE_CAPTURE = 2
  }

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  fun openCamera(promise: Promise) {
    val currentActivity = currentActivity
    if (currentActivity != null) {
      if (ContextCompat.checkSelfPermission(currentActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
        startCamera(currentActivity)
        promise.resolve("Camera opened successfully")
      } else {
        ActivityCompat.requestPermissions(currentActivity, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
      }
    } else {
      promise.reject("ACTIVITY_ERROR", "No current activity available")
    }
  }

  @SuppressLint("QueryPermissionsNeeded")
  private fun startCamera(activity: Activity) {
    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    try {
      activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    } catch (e: IOException) {
      // Handle error
    }
  }
}
