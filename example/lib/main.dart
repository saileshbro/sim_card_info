import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:sim_card_info/sim_card_info.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _simInfo = 'Unknown';
  final _simCardInfoPlugin = SimCardInfo();

  @override
  void initState() {
    super.initState();
    initSimInfoState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initSimInfoState() async {
    String simCardInfo;
    // Platform messages may fail, so we use a try/catch PlatformException.
    // We also handle the message potentially returning null.
    try {
      simCardInfo =
          await _simCardInfoPlugin.getSimInfo() ?? 'Cannot retrieve info';
    } on PlatformException {
      simCardInfo = 'Failed to get Sim Info.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _simInfo = simCardInfo;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Sim Info'),
        ),
        body: Center(
          child: Text('$_simInfo\n'),
        ),
      ),
    );
  }
}
