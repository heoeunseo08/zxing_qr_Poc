import 'package:flutter/services.dart';

class QrController {
  MethodChannel channel = MethodChannel("qr");

  Future<void> leadQR() async => await channel.invokeMethod("lead");
}