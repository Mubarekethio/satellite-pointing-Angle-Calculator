package org.mukee.calculatorapp.pointingcalc_mukee


import android.Manifest

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.CreationExtras


class MainActivity : AppCompatActivity(), LocationListener {

    private lateinit var locationManager: LocationManager
    private lateinit var tvGpsLocation: TextView
    private lateinit var tvGpsLocation1: TextView
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var input3: EditText
    private lateinit var output1: EditText
    private val locationPermissionCode = 2
    private var backPressedTime: Long = 0
    private lateinit var az_button:TextView//findViewById<TextView>(R.id.azI)
    private lateinit var NEl_button: TextView//findViewById<TextView>(R.id.NEl2)
    private lateinit var InEl_button: TextView//findViewById<TextView>(R.id.IEl2)


    private lateinit var button: Button
    private lateinit var pro_bar: ProgressBar
    private var isProgressVisible = true

    //private lateinit var input1: EditText
    //private lateinit var input2: EditText
    //private lateinit var output1: EditText








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        output1= findViewById(R.id.output1)
        input3 = findViewById(R.id.input3)
        az_button= findViewById(R.id.azI)
        NEl_button= findViewById(R.id.NEl2)
        InEl_button=findViewById(R.id.IEl2)
        pro_bar= findViewById(R.id.probar12)
        button = findViewById(R.id.getLocation)

        // get reference to the string array that we just created
        val languages = resources.getStringArray(R.array.programming_languages)
        // create an array adapter and pass the required parameter // in our case pass the context, drop down layout , and array.
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, languages)
        // get reference to the autocomplete text view
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        // set adapter to the autocomplete tv to the arrayAdapter  //println(autocompleteTV.toString())
        autocompleteTV.setAdapter(arrayAdapter)


        //to get selected value add item click listener
        autocompleteTV.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val fst= autocompleteTV.text.toString().indexOf("(")
            val lst= autocompleteTV.text.toString().indexOf("°")
            val angle = autocompleteTV.text.toString().subSequence(fst+1,lst)

            val dc= "West"
            if (autocompleteTV.text.toString().contains(dc)){
                //f_angle= -+angle
                input3.setText("-$angle")
                //println("-"+ang)
            }else{
                input3.setText(angle)
            }


            Toast.makeText(
                applicationContext,
                "" + autocompleteTV.text.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
        //Find Current Location
        //val button: Button = findViewById(R.id.getLocation)






        button.setOnClickListener {
            //pro_bar.visibility = View.VISIBLE
            //input1.text.clear()
            //input2.text.clear()

            if (input1.text == null || input2.text.isNotEmpty()) {
                button.text="Get Loc"
                getLocation()

            }else{
                button.text="Loading........"
                //getLocation()
                getLocation()
            }

            //if (input1.text.isNullOrEmpty() && input2.text.isNullOrBlank()){
                //button.text="Loading......"
                //getLocation()
                //getLocation()
            //}else{
                //input1.text.clear()
                //input2.text.clear()

                //button.text="Loading!!!!"
                //getLocation()
            //}
            //input1.text.clear()
            //isProgressVisible= false
            //}

            //isProgressVisible=true
            //getLocation()
            //if(isProgressVisible){
                //button.text="Loading"
            //}

        }

        val btncalculate = findViewById<Button>(R.id.btncalculate)
        btncalculate.setOnClickListener {
            calculatePointing()
        }

        val btnclear = findViewById<Button>(R.id.btnclear)
        btnclear.setOnClickListener() {
            autocompleteTV.text.clear()
            input1.text=null
            input2.text.clear()
            input3.text.clear()
            output1.text.clear()
            az_button.text=""
            NEl_button.text=""
            InEl_button.text=""
            button.text="Get Location c"


        }
    }

    // To Calculate the Pointing Angle
    private fun calculatePointing(){

        val output1 = findViewById<TextView>(R.id.output1)
        try {
            //println(autocompleteTV.text.toString())
            val Az: String = PointingClass(input1.text.toString().toDouble(),input2.text.toString().toDouble(),input3.text.toString().toDouble()).Azimuth
            val El: String= PointingClass(input1.text.toString().toDouble(),input2.text.toString().toDouble(),input3.text.toString().toDouble()).normal_El
            val El1: String = PointingClass(input1.text.toString().toDouble(),input2.text.toString().toDouble(),input3.text.toString().toDouble()).inverted_El

            az_button.text= Az
            NEl_button.text= El.toString()
            InEl_button.text = El1.toString()

            output1.text = "Azimuth :\t${Az}\n" +
                            "Normal Elevation:\t${El}\n" +
                            "Inverted Elevation:\t${El1}"


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
        //tvGpsLocation = findViewById(R.id.input1)
        //tvGpsLocation1= findViewById(R.id.input2)
        //input1.setText(tvGpsLocation.text)

        input1.setText(location.longitude.toString())
        input2.setText(location.latitude.toString())
        button.text="Get Location"


        //tvGpsLocation.text= location.longitude.toString()
        //tvGpsLocation1.text = location.latitude.toString()

        //if (input1.text.isNotEmpty()){
            //button.text="Get Location"
            //input1.text.clear()
            //isProgressVisible= false
        //}



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











