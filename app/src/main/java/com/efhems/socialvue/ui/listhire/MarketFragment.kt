package com.efhems.socialvue.ui.listhire


import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.efhems.socialvue.R
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.IOException

/*Thanks to this tutorial
*
* https://www.raywenderlich.com/230-introduction-to-google-maps-api-for-android-with-kotlin#toc-anchor-010
* */

class MarketFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    private val TAG = MarketFragment::class.java.name

    //Receiving Location Updates
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private var locationUpdateState = false
    companion object {

        private const val LOCATION_PERMISSION_REQUEST_CODE = 1

        private const val REQUEST_CHECK_SETTINGS = 2

        private const val PLACE_PICKER_REQUEST = 3

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view  = inflater.inflate(R.layout.fragment_market, container, false)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.requireActivity())

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            Toast.makeText(this.context, "Place picker commented out", Toast.LENGTH_SHORT).show()
            //loadPlacePicker()
        }

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)

                lastLocation = p0.lastLocation
                //placeMarkerOnMap(LatLng(lastLocation.latitude, lastLocation.longitude))
            }
        }

        createLocationRequest()

    }

    override fun onMapReady(googleMap: GoogleMap) {

        Log.i(TAG, "map is ready")

        //Initialize map
        map = googleMap

        /*val newYork = LatLng(40.73, -73.99)  // this is New York
        map.addMarker(MarkerOptions().position(newYork).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(newYork, 15.0f))*/

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(10F), 2000, null)

        //enable the zoom in/zoom out interface on the map
        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(this)

        //Check Permission to access User location: ACCESS_FINE_LOCATION
        setUpMap()

        //animateZoom to User location
        usersLocation()

    }


    override fun onMarkerClick(marker: Marker?): Boolean = false


    private fun usersLocation(){

        if (ActivityCompat.checkSelfPermission(this.requireContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            //set my location enabled and draw an indication of my current location
            map.isMyLocationEnabled = true

            //The Android Maps API provides different map types: MAP_TYPE_NORMAL, MAP_TYPE_SATELLITE, MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID
            map.mapType = GoogleMap.MAP_TYPE_TERRAIN

            fusedLocationClient.lastLocation.addOnSuccessListener(this.requireActivity()) { location ->
                // Got last known location. In some rare situations this can be null. so, if it is not null, zoom camera to the location
                location?.let {
                    lastLocation = it
                    val currentLatLng = LatLng(it.latitude, it.longitude)
                    placeMarkerOnMap(currentLatLng)
                    val first = LatLng(4.6467, 7.9429)
                    placeMarkerOnMap(first)
                    val second = LatLng(4.6567, 7.9429)
                    placeMarkerOnMap(second)
                    val third = LatLng(4.6867, 7.9429)
                    placeMarkerOnMap(third)
                    val fourth = LatLng(4.6477, 7.9429)
                    placeMarkerOnMap(fourth)
                    val five = LatLng(4.6467, 7.9429)
                    placeMarkerOnMap(five)
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                }
            }

        }

    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this.requireContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.requireActivity(),
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
    }

    private fun placeMarkerOnMap(latLng: LatLng) {
        //map.clear() // Removes all previous markers, polylines, polygons, overlays, etc from the map.

        val markerOptions = MarkerOptions().position(latLng)
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.locaion_marker)))
        markerOptions.title(getAddress(latLng)).flat(true)/*.rotation(60.0F)*/
        map.addMarker(markerOptions)
    }

    //let Geocoder get Address using LatLng
    private fun getAddress(latLng: LatLng): String {
        val geocoder = Geocoder(this.requireContext())
        val addresses: List<Address>?
        val address: Address?
        var addressText = ""
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            if (null != addresses && addresses.isNotEmpty()) {
                address = addresses[0]
                for (i in 0 until address.maxAddressLineIndex) {
                    addressText += if (i == 0) address.getAddressLine(i) else "\n" + address.getAddressLine(i)
                }
            }
        } catch (e: IOException) {
            Log.e(TAG, e.localizedMessage)
        }

        return addressText
    }


    //Receiving Location Updates: From here to onResume + onCreate method
    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this.requireContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.requireActivity(),
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null /* Looper */)
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)

        //Before you start requesting for location updates,
        //you need to check the state of the user’s location settings.
        val client = LocationServices.getSettingsClient(this.requireActivity())
        val task = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            locationUpdateState = true
            startLocationUpdates()
        }
        task.addOnFailureListener { e ->
            // 6
            if (e is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    e.startResolutionForResult(this.requireActivity(),
                            REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                locationUpdateState = true
                startLocationUpdates()
            }
        }

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                val place = PlacePicker.getPlace(this.requireContext(), data)
                var addressText = place.name.toString()
                addressText += "\n" + place.address.toString()

                placeMarkerOnMap(place.latLng)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onResume() {
        super.onResume()
        if (!locationUpdateState) {
            startLocationUpdates()
        }
    }


    private fun loadPlacePicker() {
        val builder = PlacePicker.IntentBuilder()

        try {
            startActivityForResult(builder.build(this.requireActivity()), PLACE_PICKER_REQUEST)
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }
    }

}

