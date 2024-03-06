package com.moduleguizbr

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.media.AudioManager
import android.os.Build
import android.provider.MediaStore
import android.view.KeyEvent
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.facebook.react.modules.core.DeviceEventManagerModule
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
  fun multiply(a: Double, b: Double, promise: Promise) {
    promise.resolve(a * b)
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
    } catch (e: Exception) {
      promise.reject(e.message)
    }
  }


  @RequiresApi(Build.VERSION_CODES.TIRAMISU)
  @ReactMethod
  fun registerMediaButtonEvent() {
    val receiver = object : BroadcastReceiver() {
      override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_MEDIA_BUTTON) {
          val event = intent.getParcelableExtra<KeyEvent>(Intent.EXTRA_KEY_EVENT)
          if (event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_HEADSETHOOK) {
            // Emitir evento para o JavaScript
            reactApplicationContext
              .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
              .emit("MediaButtonClicked", "clicado")
          }
        }
      }
    }

    val filter = IntentFilter(Intent.ACTION_MEDIA_BUTTON)
    reactApplicationContext.registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED)
  }

  @ReactMethod
  fun unregisterMediaButtonEvent() {
    val receiver = object : BroadcastReceiver() {
      override fun onReceive(context: Context?, intent: Intent?) {
        // Não é necessário fazer nada aqui, apenas serve para desregistrar o receptor
      }
    }

    val filter = IntentFilter(Intent.ACTION_MEDIA_BUTTON)
    reactApplicationContext.unregisterReceiver(receiver)
  }
}
