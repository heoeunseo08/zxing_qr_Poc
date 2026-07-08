package com.example.zxing_qr_poc

import android.content.Context
import android.graphics.BitmapFactory
import com.google.zxing.BinaryBitmap
import com.google.zxing.MultiFormatReader
import com.google.zxing.NotFoundException
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.common.HybridBinarizer
import io.flutter.FlutterInjector
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class LeadQr(private val context: Context) : MethodChannel.MethodCallHandler {
    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "lead" -> {
                val qrText = leadQR("assets/qr.png")
                result.success(qrText)
            }
            else -> result.notImplemented()
        }
    }

    private fun leadQR(path: String): String? {
        val loader = FlutterInjector.instance().flutterLoader()
        val assetKey = loader.getLookupKeyForAsset(path)
        val inputStream = context.assets.open(assetKey)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val width = bitmap.width
        val height = bitmap.height
        val pixels = IntArray(width * height)
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height)

        val source = RGBLuminanceSource(width, height, pixels)
        val binaryBitmap = BinaryBitmap(HybridBinarizer(source))

        return try {
            MultiFormatReader().decode(binaryBitmap).text
        } catch (e: NotFoundException) {
            null
        }
    }
}