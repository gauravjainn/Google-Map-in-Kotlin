package com.app.googlemapinkotlin

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric


class MainApplication : MultiDexApplication() {

    private val TAG = "GoogleKMap"
    override fun onCreate() {
        super.onCreate()
        // set up crash analytics in project
        Fabric.with(this, Crashlytics())
        Crashlytics.log(Log.VERBOSE, TAG, "crash")
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        //to support method over 64k
        MultiDex.install(this)
    }



/*    We have to enable 3 api in cloud console to show polyline on google map*/
/*    Directions API
    Places API
    Geocoding API*/
}