package com.publisher.helloworld

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.PackageManagerCompat.LOG_TAG
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import com.appsflyer.AFInAppEventType // Predefined event names
import com.appsflyer.AFInAppEventParameterName // Predefined parameter names



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialization
        AppsFlyerLib.getInstance().init("gW3pEKR5ASqYanXW8dWxEG", null, this)

        //Log
        AppsFlyerLib.getInstance().setDebugLog(true)

        AppsFlyerLib.getInstance().start(this, "gW3pEKR5ASqYanXW8dWxEG", object :
            AppsFlyerRequestListener {
            @SuppressLint("RestrictedApi")
            override fun onSuccess() {
                Log.d(LOG_TAG, "Launch sent successfully")
            }


            @SuppressLint("RestrictedApi")
            override fun onError(errorCode: Int, errorDesc: String) {
                Log.d(LOG_TAG, "Launch failed to be sent:\n" +
                        "Error code: " + errorCode + "\n"
                        + "Error description: " + errorDesc)
            }
        })



        //Event
        val eventValues = HashMap<String, Any>()
        eventValues.put(AFInAppEventParameterName.CONTENT_ID, "123457")
        eventValues.put(AFInAppEventParameterName.CONTENT_TYPE, "Shirt")
        eventValues.put(AFInAppEventParameterName.REVENUE, 200)
        eventValues.put(AFInAppEventParameterName.CURRENCY,"USD")

        //Log
        AppsFlyerLib.getInstance().logEvent(getApplicationContext(),
            AFInAppEventType.PURCHASE,
            eventValues,
            object : AppsFlyerRequestListener {
                @SuppressLint("RestrictedApi")
                override fun onSuccess() {
                    Log.d(LOG_TAG, "Event sent successfully")
                }
                @SuppressLint("RestrictedApi")
                override fun onError(errorCode: Int, errorDesc: String) {
                    Log.d(LOG_TAG, "Event failed to be sent:\n" +
                            "Error code: " + errorCode + "\n"
                            + "Error description: " + errorDesc)
                }
            })
    }
}