package com.app.googlemapinkotlin.Path

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.app.googlemapinkotlin.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class MainActivity : AppCompatActivity(), OnMapReadyCallback, TaskLoadedCallback {

    private var mMap: GoogleMap? = null
    private var place1: MarkerOptions? = null
    private var place2: MarkerOptions? = null
    internal lateinit var getDirection: Button
    private var currentPolyline: Polyline? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.app.googlemapinkotlin.R.layout.activity_main)

        getDirection = findViewById(com.app.googlemapinkotlin.R.id.btnGetDirection)
     /*   click listener used to make polyline betwwn 2 places*/
        getDirection.setOnClickListener {
            /*Api calling*/
            FetchURL(this@MainActivity).execute(
                getUrl(
                    place1!!.position,
                    place2!!.position,
                    "driving"
                ), "driving"
            )
        }
        //27.658143,85.3199503
        //27.667491,85.3208583

       /* setting lat long for marker 1(place 1) and marker 2 (place2) */
        place1 = MarkerOptions().position(LatLng(22.3039, 70.8022)).title("Location 1")
        place2 = MarkerOptions().position(LatLng(23.0225, 72.5714)).title("Location 2")

        /*intializing map on screen*/
        val mapFragment = fragmentManager
            .findFragmentById(com.app.googlemapinkotlin.R.id.mapNearBy) as MapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        Log.d("mylog", "Added Markers")
     /*   adding 2 markers on google map*/
        mMap!!.addMarker(place1)
        mMap!!.addMarker(place2)

        val googlePlex = CameraPosition.builder()
            .target(LatLng(22.7739, 71.6673))
            .zoom(7f)
            .bearing(0f)
            .tilt(45f)
            .build()

/*        moving camera to particular poisiton (above target lat long position {
            .target(LatLng(22.7739, 71.6673))})*/
        mMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 5000, null)
    }

    private fun getUrl(origin: LatLng, dest: LatLng, directionMode: String): String {
        // Origin of route
        val str_origin = "origin=" + origin.latitude + "," + origin.longitude
        // Destination of route
        val str_dest = "destination=" + dest.latitude + "," + dest.longitude
        // Mode
        val mode = "mode=$directionMode"
        // Building the parameters to the web service
        val parameters = "$str_origin&$str_dest&$mode"
        // Output format
        val output = "json"
        // Building the url to the web service
        return "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(com.app.googlemapinkotlin.R.string.google_maps_key)

 /*Url to fetch address json response*/
        /*https://maps.googleapis.com/maps/api/directions/json?origin=22.3039,70.8022&destination=23.0225,72.5714&mode=driving&key=AIzaSyDjq1w5cfXWiAQktnMNBDhErHk1CGSVneA
*/

    }

    override fun onTaskDone(vararg values: Any) {
        if (currentPolyline != null)
            currentPolyline!!.remove()
        currentPolyline = mMap!!.addPolyline(values[0] as PolylineOptions)
    }
}