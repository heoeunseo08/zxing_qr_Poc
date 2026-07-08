import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';
import 'package:zxing_qr_poc/controller/qr_controller.dart';

class AppScreen extends StatefulWidget {
  const AppScreen({super.key});

  @override
  State<AppScreen> createState() => _AppScreenState();
}

class _AppScreenState extends State<AppScreen> {
  QrController controller = QrController();
  String? result;

  @override
  void initState() {
    super.initState();
    result = null;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          SizedBox(width: MediaQuery.widthOf(context)),
          Image.asset("assets/qr.png", width: 300),
          leadButton(),
          urlButton(),
        ],
      ),
    );
  }

  Widget leadButton() => GestureDetector(
    onTap: () async {
      result = await controller.leadQR();
      setState(() {});
      log(result!);
    },
    child: Container(
      padding: EdgeInsets.symmetric(horizontal: 14, vertical: 12),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(99),
        border: Border.all(color: Colors.black),
      ),
      child: Text("QR 스캔"),
    ),
  );

  Widget urlButton() => GestureDetector(
    onTap: () async {
      await launchUrl(Uri.parse(result!));
    },
    child: Container(
      padding: EdgeInsets.symmetric(horizontal: 14, vertical: 12),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(99),
        border: Border.all(color: Colors.black),
      ),
      child: Text("접속"),
    ),
  );
}
