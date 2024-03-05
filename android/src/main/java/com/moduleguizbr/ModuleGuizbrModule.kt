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
    try {
      val audioManager = reactApplicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
      val isConnectedInput = audioManager.getDevices(AudioManager.GET_DEVICES_INPUTS);
      val isConnectedOutput = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS);
      var a = "INPUT | ";
      for (i in isConnectedInput.indices) {
        a += isConnectedInput[i].productName.toString() + " | ";
      }
      a += "OUTPUT | ";
      for (i in isConnectedOutput.indices) {
        a += isConnectedOutput[i].productName.toString() + " | ";
      }
      promise.resolve(a)
    } catch (e: IOException) {
      promise.reject(e.message)
    }
  }
}
