package com.wsc.sim_card_info


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat
import io.flutter.Log
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.EventChannel.EventSink
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.RequestPermissionsResultListener
import org.json.JSONArray
//import com.google.gson.Gson


/** SimCardInfoPlugin */
class SimCardInfoPlugin : FlutterPlugin, MethodCallHandler, ActivityAware,
    RequestPermissionsResultListener {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var activity: Activity
    private lateinit var channel: MethodChannel
    private var methodChannelName = "getSimInfo"

    private val result: Result? = null
    private val permissionEvent: EventSink? = null


    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "sim_card_info")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (call.method == methodChannelName) {
            result.success(getSimInfo())
        } else {
            result.notImplemented()
        }
    }


    @SuppressLint("HardwareIds")
    private fun getSimInfo(): String {
        val simCardInfo = StringBuilder().append("Sim Card Info:\n\n")
//    val simInfo  = SimInfo()
//    return  simInfo.getSimCardInfo(this)
        println("getSimInfo")
        val telephonyManager =
            activity.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?
        println("telephonyManager: $telephonyManager")
        if (telephonyManager == null || ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return "null"
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            println("Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q")
            val subscriptionManager = activity.getSystemService(SubscriptionManager::class.java)
            subscriptionManager?.activeSubscriptionInfoList?.let { subscriptionInfoList ->
                simCardInfo.append("[")
                for (info in subscriptionInfoList) {
                    simCardInfo.append(
                        "{" +
                                "\"carrierName\": \"${info.carrierName}\"," +
                                "\"displayName\": \"${info.displayName}\"," +
                                "\"slotIndex\": \"${info.simSlotIndex}\"," +
                                "\"number\": \"${info.number}\"," +
                                "\"countryIso\": \"${info.countryIso}\"," +
                                "\"countryPhonePrefix\": \"${info.countryIso}\"" +
                                "},"
                    )
                }
                // Remove the trailing comma and close the JSON array
                if (simCardInfo.last() == ',') {
                    simCardInfo.deleteCharAt(simCardInfo.length - 1)
                }
                simCardInfo.append("]")
            }
        } else {
            simCardInfo.append("[")
            simCardInfo.append(
                "{" +
                        "\"carrierName\": \"${telephonyManager.networkOperatorName}\"," +
                        "\"displayName\": \"${telephonyManager.simOperatorName}\"," +
                        "\"slotIndex\": \"${telephonyManager.simSerialNumber}\"," +
                        "\"number\": \"${telephonyManager.line1Number}\"," +
                        "\"countryIso\": \"${telephonyManager.simCountryIso}\"," +
                        "\"countryPhonePrefix\": \"${telephonyManager.simCountryIso}\"," +
                        "},"
            )
            // Remove the trailing comma and close the JSON array
            if (simCardInfo.last() == ',') {
                simCardInfo.deleteCharAt(simCardInfo.length - 1)
            }
            simCardInfo.append("]")
        }
        println("simCardInfo: $simCardInfo")
        return simCardInfo.toString()
    }


    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity
        binding.addRequestPermissionsResultListener(this)
    }

    override fun onDetachedFromActivityForConfigChanges() {
        TODO("Not yet implemented")
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        activity = binding.activity
        binding.addRequestPermissionsResultListener(this)
    }

    override fun onDetachedFromActivity() {
        TODO("Not yet implemented")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ): Boolean {

        // If request is cancelled, the result arrays are empty.
        if (requestCode == 0) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionEvent?.success(true)
                getSimInfo()
                return true
            } else {
                permissionEvent?.success(false)
            }
        }
        result?.error("PERMISSION", "onRequestPermissionsResult is not granted", null)
        return false
    }

}

data class SimInfo(
    val carrierName: String = "",
    val displayName: String = "",
    val slotIndex: Int = 0,
    val number: String = "",
    val countryIso: String = "",
    val countryPhonePrefix: String = ""
) {}