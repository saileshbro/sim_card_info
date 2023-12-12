//package com.wsc.sim_card_info;
//
//
////import androidx.appcompat.app.AppCompatActivity;
//
//import static androidx.core.app.ActivityCompat.requestPermissions;
//
//import androidx.core.app.ActivityCompat;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.os.Build;
//import android.telephony.SubscriptionInfo;
//import android.telephony.SubscriptionManager;
//import android.telephony.TelephonyManager;
//import android.widget.Toast;
//
////import com.example.simcardinfo.receiver.SimCardReceiver;
////import com.example.simcardinfo.wrapper.TelephonyManagerWrapper;
//
//
//import java.util.List;
//
///*
//╔═══════════════════════════════════════════════════╗
//║ Created by ASUS on 12/11/2023 at 4:04 PM.         ║
//║═══════════════════════════════════════════════════║
//║ fady.fouad.a@gmail.com.                           ║
//╚═══════════════════════════════════════════════════╝
//*/
//
//
//class SimInfo {
//    SimInfo() {
//    }
//
//    private static String simInfo;
//
//
//    @SuppressLint("HardwareIds")
//    public String getSimCardInfo(Activity context) {
//
////        Toast.makeText(context, "getSimCardInfo", Toast.LENGTH_SHORT).show();
////        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
////        if (telephonyManager != null) {
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
////                /// For Android Q and above
////                SubscriptionManager subscriptionManager = context.getSystemService(SubscriptionManager.class);
////                if (subscriptionManager != null) {
////                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
////                        Toast.makeText(context, "Permission Not Granted", Toast.LENGTH_SHORT).show();
////                        Toast.makeText(context, "Requesting Permission", Toast.LENGTH_SHORT).show();
////                        requestPermissions(context, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
////                        Toast.makeText(context, "Permission" + ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE), Toast.LENGTH_SHORT).show();
////                        return null;
////                    }
////                    List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
////                    if (subscriptionInfoList != null && !subscriptionInfoList.isEmpty()) {
////                        StringBuilder simCardInfo = new StringBuilder();
////                        for (SubscriptionInfo info : subscriptionInfoList) {
////                            simCardInfo.append("Name: ").append(info.getCarrierName()).append("\n").append("Number: ").append(info.getNumber()).append("\n\n");
////                        }
////                        Toast.makeText(context, "simCardInfo: \n" + simCardInfo.toString(), Toast.LENGTH_SHORT).show();
////                        simInfo = simCardInfo.toString();
////                    }
////                }
////            } else if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
////                StringBuilder simCardInfo = new StringBuilder();
////                Toast.makeText(context, "Granted", Toast.LENGTH_SHORT).show();
////                simCardInfo.append("Name: ").append(telephonyManager.getSimOperatorName()).append("\n").append("Number: ").append(telephonyManager.getLine1Number()).append("\n\n");
////                Toast.makeText(context, "simCardInfo: \n" + simCardInfo.toString(), Toast.LENGTH_SHORT).show();
////                simInfo = simCardInfo.toString();
////            }
////        }
////        return simInfo;
////    }
////}
