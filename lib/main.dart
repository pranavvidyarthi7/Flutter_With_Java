import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'dart:async';

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(
        title: Text(
          'Launch Camera!',
          style: TextStyle(
            color: Colors.teal,
          ),
        ),
        backgroundColor: Colors.white,
      ),
      body: NativeStuff(),
    ),
  ));
}

class NativeStuff extends StatefulWidget {
  @override
  NativeStuffState createState() {
    return NativeStuffState();
  }
}

class NativeStuffState extends State<NativeStuff> {
  static const platformMethodChannel = const MethodChannel('CAMERA');

  Future<void> _getValues() async {
    try {
      final result = await platformMethodChannel.invokeMethod('camera');
      print('r: $result');
    } on PlatformException catch (e) {
      print(e.toString());
    }
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          MaterialButton(
            child: Text('Get Values'),
            onPressed: _getValues,
          ),
        ],
      ),
    );
  }
}
