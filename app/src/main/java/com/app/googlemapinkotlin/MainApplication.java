package com.app.googlemapinkotlin;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;


public class MainApplication extends Application {

    private static final String TAG = "styleNSity";

    @Override
    public void onCreate() {
        super.onCreate();
        // set up crash analytics in project
        Fabric.with(this, new Crashlytics());
        Crashlytics.log(Log.VERBOSE, TAG, "crash");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }
}