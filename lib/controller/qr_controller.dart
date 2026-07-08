import 'package:flutter/services.dart';

class QrController {
  MethodChannel channel = MethodChannel("qr_channel");

  Future<String> leadQR() async => await channel.invokeMethod("lead");
}