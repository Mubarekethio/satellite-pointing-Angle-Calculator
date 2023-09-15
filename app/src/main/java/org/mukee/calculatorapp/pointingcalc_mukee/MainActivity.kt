package org.mukee.calculatorapp.pointingcalc_mukee


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.mukee.calculatorapp.pointingcalc_mukee.pointingClass


class MainActivity : AppCompatActivity(), LocationListener {

    private lateinit var locationManager: LocationManager
    private lateinit var tvGpsLocation: TextView
    private lateinit var tvGpsLocation1: TextView
    private val locationPermissionCode = 2
    private var backPressedTime: Long = 0
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var output1: EditText






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val input11 = findViewById<EditText>(R.id.input1)
        val input22 = findViewById<EditText>(R.id.input2)
        val output11= findViewById<EditText>(R.id.output1)
        val input3 = findViewById<EditText>(R.id.input3)

        // get reference to the string array that we just created
        val languages = resources.getStringArray(R.array.programming_languages)
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, languages)
        // get reference to the autocomplete text view
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        // set adapter to the autocomplete tv to the arrayAdapter
        //println(autocompleteTV.toString())
        autocompleteTV.setAdapter(arrayAdapter)


        //to get selected value add item click listener
        //to get selected value add item click listener
        autocompleteTV.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val delimiter = "("
            val dem= "°"
            val parts = autocompleteTV.text.toString().split(delimiter)
            val p=parts[1].split(dem)
            val dc= "West"
            if (parts[1].contains(dc)){
                //print("-"+p[0])
                input3.setText("-${p[0]}")
            }else {
                input3.setText(p[0])
            }
            Toast.makeText(
                applicationContext,
                "" + autocompleteTV.text.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
        //Find Current Location
        val button: Button = findViewById(R.id.getLocation)
        button.setOnClickListener {
            getLocation()
        }



        val btncalculate = findViewById<Button>(R.id.btncalculate)
        btncalculate.setOnClickListener {
            calculatePointing()

        }

        val btnclear = findViewById<Button>(R.id.btnclear)
        btnclear.setOnClickListener() {
            autocompleteTV.text.clear()
            input11.text.clear()
            input22.text.clear()
            input3.text.clear()
            output11.text.clear()
        }
    }

    // To Calculate the Pointing Angle
    private fun calculatePointing(){

        val output1 = findViewById<TextView>(R.id.output1)
        try {
            val input1 = (findViewById<EditText>(R.id.input1)).text.toString().toDouble()
            val input2 = (findViewById<EditText>(R.id.input2)).text.toString().toDouble()
            val input3= findViewById<EditText>(R.id.input3).text.toString().toDouble()
            //val B5 = input1.text.toString().toDouble()
            //val C5 = input2.text.toString().toDouble()
            //val D5 = input3.text.toString().toDouble()

            //println(autocompleteTV.text.toString())
            val Az = pointingClass(input1, input2, input3).Az
            val El= pointingClass(input1, input2, input3).El
            val El1 = pointingClass(input1, input2, input3).El1

            when {
                input1.toString().isNotEmpty() || input2.toString()
                    .isNotEmpty() || input3.toString().isNotEmpty() -> {
                    with(output1) {
                        text =
                            "Azimuth :\t${Az}\nNormal Elevation:\t${El}\nInverted Elevation:\t${El1}"
                    }
                }

                else -> println("")
            }
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            output1.text = ""
        }

    }


    //To get current Location
    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }

    override fun onLocationChanged(location: Location) {
        tvGpsLocation = findViewById(R.id.input1)
        tvGpsLocation1= findViewById(R.id.input2)
        //input1.setText(tvGpsLocation.text)
        tvGpsLocation.text= location.longitude.toString()
        tvGpsLocation1.text = location.latitude.toString()

        //tvGpsLocation.text = "Latitude: " + location.latitude + " , Longitude: " + location.longitude
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {

        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(this, "Press back again to leave the app.", Toast.LENGTH_LONG).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}











