package org.mukee.calculatorapp.pointingcalc_mukee


import android.Manifest

import android.content.Context

import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Parcel
import android.os.Parcelable
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




class MainActivity() :AppCompatActivity(),LocationListener, Parcelable {

    private var tvGlat: String?=null
    private var tvGlog: String?=null
    private var laMin: String?=null
    private var loMin: String?=null
    private lateinit var locationManager: LocationManager
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var input3: EditText
    private val locationPermissionCode = 2
    private var backPressedTime: Long = 0
    private lateinit var az_button:TextView
    private lateinit var NEl_button: TextView
    private lateinit var InEl_button: TextView
    private lateinit var latM:TextView
    private lateinit var longM: TextView


    //"latMin"


    private lateinit var button: Button
    //private lateinit var pro_bar: ProgressBar

    constructor(parcel: Parcel) : this() {
        backPressedTime = parcel.readLong()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        //output1= findViewById(R.id.output1)
        input3 = findViewById(R.id.input3)
        az_button= findViewById(R.id.azI)
        NEl_button= findViewById(R.id.NEl2)
        InEl_button=findViewById(R.id.IEl2)
        //pro_bar= findViewById(R.id.probar12)
        button = findViewById(R.id.getLocation)
        latM =findViewById(R.id.latMin)
        longM =findViewById(R.id.longMin)









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
                input3.setText(getString(R.string.angle, angle))
                //println("-"+ang)
            }else{
                input3.setText(angle)
            }
            Toast.makeText(
                applicationContext, "" + autocompleteTV.text.toString(), Toast.LENGTH_SHORT).show()
        }

        button.setOnClickListener {
            //getLocation()
            if (tvGlog.isNullOrEmpty() && tvGlat.isNullOrEmpty()) {
                "Loading......".also { button.text = it }
                getLocation()

            }else{
                input1.text.clear()
                input2.text.clear()
                latM.text=""
                longM.text=""
                "Loading....".also { button.text = it }
                Handler().postDelayed({
                    // yourMethod()
                    input1.setText(buildString { append(tvGlog) })
                    input2.setText(buildString { append(tvGlat) })

                    latM.text= laMin
                    longM.text= loMin

                    "Get Location".also { button.text = it }
                }, 2000)
            }
        }

        val btncalculate = findViewById<Button>(R.id.btncalculate)
        btncalculate.setOnClickListener {
            calculatePointing()
        }

        val btnclear = findViewById<Button>(R.id.btnclear)
        btnclear.setOnClickListener{
            autocompleteTV.text.clear()
            input1.text.clear()
            input2.text.clear()
            input3.text.clear()
            longM.text=""
            latM.text=""
            az_button.text=""
            NEl_button.text=""
            InEl_button.text=""
        }
    }

    // To Calculate the Pointing Angle
    private fun calculatePointing(){
        try {
            //println(autocompleteTV.text.toString())
            val Az: String = PointingClass(input1.text.toString().toDouble(),input2.text.toString().toDouble(),input3.text.toString().toDouble()).Azimuth
            val El: String= PointingClass(input1.text.toString().toDouble(),input2.text.toString().toDouble(),input3.text.toString().toDouble()).normal_El
            val El1: String = PointingClass(input1.text.toString().toDouble(),input2.text.toString().toDouble(),input3.text.toString().toDouble()).inverted_El

            az_button.text= Az
            NEl_button.text= El
            InEl_button.text = El1

        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            //output1.text = ""
        }
    }

    //To get current Location
    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1f, this)

    }
    override fun onLocationChanged(location: Location) {
        tvGlog = location.longitude.toString()
        tvGlat= location.latitude.toString()



        val fst1= tvGlog!!.indexOf(".")
        val long02= tvGlog!!.subSequence(0,fst1).toString()+'°'
        val sr12=(((tvGlog!!.subSequence(fst1, tvGlog!!.length)).toString().toDouble())*60).toString()
        val fst2= sr12.indexOf(".")
        val long03= (sr12.subSequence(0,fst2)).toString()+"'"
        val sr52=((sr12.subSequence(fst2, sr12.length)).toString().toDouble())*60
        val long72= String.format("%.1f", sr52)+"''"
        //println(sr02+sr03+lt72)
        loMin= long02+ long03 +long72


        val fst= tvGlat!!.indexOf(".")
        val lat0= (tvGlat!!.subSequence(0,fst)).toString()+'°'
        val sr1=(((tvGlat!!.subSequence(fst, tvGlat!!.length)).toString().toDouble())*60).toString()
        val fst3= sr12.indexOf(".")
        val lat10=(sr1.subSequence(0,fst3)).toString()+"'"
        // println(sr10)
        val sr5=((sr1.subSequence(fst3, sr1.length)).toString().toDouble())*60
        val lat7= String.format("%.1f", sr5)+"''"
        //println(lt7)
        //println(sr0+sr10+lt7)
        laMin= lat0+lat10+lat7



        if (input1.text.isEmpty() && input2.text.isEmpty()){

            input1.setText(buildString {
        append(location.longitude.toString())
    })
            longM.text = buildString {
                append(long02)
                append(long03)
                append(long72)
            }
            //input1.setText(location.longitude.toString() +"("+ sr02 + sr03 + lt72+")")
            input2.setText(buildString {
        append(location.latitude.toString())
    })

            latM.text = buildString {
                append(lat0)
                append(lat10)
                append(lat7)
            }
            "Get Location".also { button.text = it }
        }//else{
            //input1.setText(tvGlog)
            //input2.setText(tvGlat)
            //"Get Location2".also { button.text = it }
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
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(backPressedTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }
        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

}