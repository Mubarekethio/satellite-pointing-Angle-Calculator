package org.mukee.calculatorapp.pointingcalc_mukee

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


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input1 = findViewById<EditText>(R.id.input1)
        val input2 = findViewById<EditText>(R.id.input2)
        var input3 = findViewById<EditText>(R.id.input3)
        val output1 = findViewById<TextView>(R.id.output1)
        //val sat_list = findViewById<Spinner>(R.id.sat_spinner)


        val btnclear = findViewById<Button>(R.id.btnclear)
        val btncalculate = findViewById<Button>(R.id.btncalculate)

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
            var delimiter = "("
            var dem= "°"
            var parts = autocompleteTV.text.toString().split(delimiter)
            var p=parts[1].split(dem)
            val dc= "West"
            if (parts[1].contains(dc)){
                //print("-"+p[0])
                input3.setText("-${p[0]}")
            }else {
                input3.setText(p[0])
            }



            //print(pp[0])
            Toast.makeText(
                applicationContext,
                "" + autocompleteTV.text.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }


        btncalculate.setOnClickListener() {

            try {
                val B5 = input1.text.toString().toDouble()
                val C5 = input2.text.toString().toDouble()
                val D5 = input3.text.toString().toDouble()

                //println(autocompleteTV.text.toString())


                //println(D5)
                val Az: Double = Math.toDegrees(
                    Math.atan(
                        0 - (Math.tan(Math.toRadians(D5) - Math.toRadians(B5)) / (Math.sin(
                            Math.toRadians(
                                C5
                            )
                        )))
                    )
                ) + 180
                //longsite+latsite+longSat
                val El: Double = Math.toDegrees(
                    Math.atan(
                        (Math.cos(Math.toRadians(D5) - Math.toRadians(B5)) * Math.cos(
                            Math.toRadians(
                                C5
                            )
                        ) - 0.15127) / (Math.sin(
                            Math.acos(
                                Math.cos(Math.toRadians(D5) - Math.toRadians(B5)) * Math.cos(
                                    Math.toRadians(C5)
                                )
                            )
                        ))
                    )
                )
                val El1: Double = 90 - (El + 22)

                when {
                    input1.toString().isNotEmpty() || input2.toString()
                        .isNotEmpty() || input3.toString().isNotEmpty() -> {
                        with(output1) {
                            text =
                                "Azimuth :\t" + Az.toString() + "\nNormal Elevation:\t" + El.toString() + "\nInverted Elevation:\t" + El1.toString()
                        }
                        //val inputMethodManager =
                            //getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

                        // on below line hiding our keyboard.
                        //inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
                        //val mgr = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        //mgr.hideSoftInputFromWindow(curEditText.getWindowToken(), 0)//Append(getText(Az.toString()), getText(Az.toString())
                    }

                    else -> println("")
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
                output1.text = ""
            }


            btnclear.setOnClickListener() {
                //clearall()
                input1.text.clear()
                input2.text.clear()
                input3.text.clear()
                output1.text = ""
            }


        }

    }
}











