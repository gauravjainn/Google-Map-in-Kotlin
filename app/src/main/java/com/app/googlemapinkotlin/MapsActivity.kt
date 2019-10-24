package com.app.googlemapinkotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        /*to show zoom in zoom out button in google map*/
        mMap.getUiSettings().setZoomControlsEnabled(true);
        /*to show current location on google map*/
        mMap.isMyLocationEnabled = true
         /*Add a marker to given location  and move the camera*/
        val latLng = LatLng(37.0902, 95.7129)
        mMap.addMarker(MarkerOptions().position(latLng).title("united states"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10F));


    }




}
