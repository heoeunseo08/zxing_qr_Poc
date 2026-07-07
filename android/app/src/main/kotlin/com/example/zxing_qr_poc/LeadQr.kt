package com.example.zxing_qr_poc

import android.content.Context
import android.graphics.BitmapFactory
import io.flutter.FlutterInjector
import com.google.zxing.RGBLuminanceSource
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class LeadQr(private val context: Context) : MethodChannel.MethodCallHandler{
    override fun onMethodCall(call: MethodCall, p1: MethodChannel.Result) {
        when(call.method){
            "lead" -> {
                val qrText = leadQR("assets/qr.png")
            }
        }
    }

    private fun leadQR(path: String): String? {
        val  loader = FlutterInjector.instance().flutterLoader()
        val assetKey = loader.getLookupKeyForAsset(path)
        val inputStream = context.assets.open(assetKey)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val width = bitmap.width
        val height = bitmap.height
        val pixels = IntArray(width*height)
        bitmap.getPixels(pixels, 0, width,0,0, width,height)

        val  source = RGBLuminanceSource()
    }
}