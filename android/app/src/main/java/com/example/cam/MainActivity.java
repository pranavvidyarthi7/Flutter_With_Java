package com.example.cam;
import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;

public class MainActivity extends FlutterActivity {
  private static final String CHANNEL = "CAMERA";

  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
  super.configureFlutterEngine(flutterEngine);
    new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
        .setMethodCallHandler(
          (call, result) -> {
            if(call.method.equals("camera")){
              float[] cc=getcc();
              result.success(cc);
            } 
            else {
              result.notImplemented();
            }
          }
        );
  }
  private float[] getcc(){
    float[] chars={};
    CameraManager manager=(CameraManager)getSystemService(CAMERA_SERVICE);
    try {
  for (String cameraId : manager.getCameraIdList()) {
    CameraCharacteristics cc
       = manager.getCameraCharacteristics(cameraId);
       if(cc.get(CameraCharacteristics.LENS_FACING)==CameraCharacteristics.LENS_FACING_FRONT){
        System.out.print("VALUES: ");
        System.out.print(cc.get(CameraCharacteristics.LENS_INTRINSIC_CALIBRATION));
        System.out.print("VALUE: ");
        System.out.println(cc.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES));
        chars=cc.get(CameraCharacteristics.LENS_INTRINSIC_CALIBRATION);
        break;
       }
}} catch (CameraAccessException e) {
  e.printStackTrace();
}
return chars;
  }
}