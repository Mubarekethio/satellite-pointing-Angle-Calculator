package org.mukee.calculatorapp.pointingcalc_mukee

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var input3: EditText
    private lateinit var offset1: EditText
    private lateinit var ht: EditText
    private lateinit var resultView: TextView
    private lateinit var obstacleResult: TextView
    //private lateinit var getLocationButton: Button
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var getLocation: FloatingActionButton
    private lateinit var calculateButton: FloatingActionButton
    private lateinit var clearButton: FloatingActionButton

    private var offsetA: String = ""
    private var height: String = ""

    private val locationPermissionCode = 2

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Initialize UI elements
        input1 = findViewById(R.id.siteLongitude)
        input2 = findViewById(R.id.siteLatitude)
        input3 = findViewById(R.id.satelliteLongitude)
        offset1 = findViewById(R.id.offsetAngle)
        ht = findViewById(R.id.height)
        obstacleResult = findViewById(R.id.obstacleResult1)
        resultView = findViewById(R.id.azimuthView)
        //getLocationButton = findViewById(R.id.getLocation)
        //val btnCalculate = findViewById<Button>(R.id.calculateButton)
        //val btnClear = findViewById<Button>(R.id.clearButton)

        // Initialize FloatingActionButtons
        getLocation = findViewById(R.id.getLocation)
        calculateButton = findViewById(R.id.calculateButton)
        clearButton = findViewById(R.id.clearButton)



        // Initialize dropdown for satellite selection
        val satellite = resources.getStringArray(R.array.satellite_positions)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, satellite)
        val satView = findViewById<AutoCompleteTextView>(R.id.satelliteDropdown)
        satView.setAdapter(arrayAdapter)

        // Handle satellite selection
        satView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, _, _ ->
                val fst = satView.text.toString().indexOf("(")
                val lst = satView.text.toString().indexOf("°")
                val angle = satView.text.toString().subSequence(fst + 1, lst)

                if (satView.text.toString().contains("West")) {
                    input3.setText(getString(R.string.angle, angle))
                } else {
                    input3.setText(angle)
                }
                Toast.makeText(applicationContext, "" + satView.text.toString(), Toast.LENGTH_SHORT).show()
            }


        // Handle get location button click
        getLocation.setOnClickListener {
            checkGPSAndFetchLocation()
        }
        // Handle calculate button click

        calculateButton.setOnClickListener {
            calculatePointing()
            if (ht.text.isNullOrEmpty()) {
                obstacleResult.text = ""
            }
        }

        // Handle clear button click
        clearButton.setOnClickListener {
            clearUI()
            satView.text.clear()
        }
    }

    // ✅ Check if GPS is enabled
    private fun checkGPSAndFetchLocation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!isGPSEnabled) {
            // GPS is disabled, prompt user to enable it manually
            Toast.makeText(this, "Please enable GPS for location access", Toast.LENGTH_LONG).show()
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        } else {
            // GPS is enabled, fetch location
            getLocation()
        }
    }

    // ✅ Get device location
    @SuppressLint("MissingPermission")
    private fun getLocation() {
        // Check for location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode
            )
            return
        }

        // Try getting last known location first
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    updateLocationUI(location)
                } else {
                    requestNewLocation()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to get last location: ${e.message}", Toast.LENGTH_SHORT).show()
                requestNewLocation()
            }
    }

    // ✅ Request new location updates
    @SuppressLint("MissingPermission")
    private fun requestNewLocation() {
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000)
            .setMinUpdateIntervalMillis(2000)
            .build()

        fusedLocationClient.requestLocationUpdates(locationRequest, object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    updateLocationUI(location)
                }
            }
        }, Looper.getMainLooper())
    }

    // ✅ Update UI with location data
    private fun updateLocationUI(location: Location) {
        val formattedLongitude = String.format("%.5f", location.longitude)
        val formattedLatitude = String.format("%.5f", location.latitude)

        input1.setText(formattedLongitude)
        input2.setText(formattedLatitude)
    }

    // ✅ Calculate pointing angles
    @SuppressLint("SetTextI18n")
    private fun calculatePointing() {
        try {
            offsetA = if (offset1.text.isNullOrEmpty()) "22.3" else offset1.text.toString()
            height = if (ht.text.isNullOrEmpty()) "0" else ht.text.toString()

            val pointing = PointingClass(
                input1.text.toString().toDouble(),
                input2.text.toString().toDouble(),
                input3.text.toString().toDouble(),
                offsetA.toDouble(),
                height.toDouble()
            )

            resultView.text = "Azimuth: ${pointing.azimuth} \t Elevation: ${pointing.elevation}\n" +
                    "Inclinometer Reading\n\tCenter Feed Antenna: ${pointing.centr_El}\n" +
                    "\tNormal dish: ${pointing.normal_El}\n\tInverted dish: ${pointing.invert_El}"

            obstacleResult.text = "Obstacle clearance in meters:\n" +
                    "\t0° clearance: ${pointing.noClear}m\n" +
                    "\t5° clearance (min): ${pointing.minClear}m\n" +
                    "\t10° clearance (recommended): ${pointing.recClear}m"

        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    // ✅ Clear function
    private fun clearUI() {
        input1.text.clear()
        input2.text.clear()
        input3.text.clear()
        offset1.text.clear()
        ht.text.clear()
        resultView.text = ""
        obstacleResult.text = ""
    }

    // ✅ Handle permission request result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            checkGPSAndFetchLocation()
        } else {
            Toast.makeText(this, "Permission denied. Enable location access.", Toast.LENGTH_LONG).show()
        }
    }
}
