package com.app.googlemapinkotlin

import android.app.Application
import android.content.Context
import android.util.Log
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric


class MainApplication : Application() {

    private val TAG = "GoogleKMap"
    override fun onCreate() {
        super.onCreate()
        // set up crash analytics in project
        Fabric.with(this, Crashlytics())
        Crashlytics.log(Log.VERBOSE, TAG, "crash")
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

    }

}