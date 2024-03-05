package com.moduleguizbr

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioManager
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
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

  @RequiresApi(Build.VERSION_CODES.M)
  @ReactMethod
  fun isHeadphonesConnected(promise: Promise) {
    promise.resolve("oi")
  }
}
