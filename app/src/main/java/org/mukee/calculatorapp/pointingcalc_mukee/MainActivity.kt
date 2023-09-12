package org.mukee.calculatorapp.pointingcalc_mukee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import org.jetbrains.annotations.NotNull
import java.nio.file.DirectoryNotEmptyException


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input1 = findViewById<EditText>(R.id.input1)
        val input2 = findViewById<EditText>(R.id.input2)
        val input3 = findViewById<EditText>(R.id.input3)
        val output1 = findViewById<TextView>(R.id.output1)
        val sat_list = findViewById<Spinner>(R.id.sat_spinner)


        val btnclear = findViewById<Button>(R.id.btnclear)
        val btncalculate = findViewById<Button>(R.id.btncalculate)

        btncalculate.setOnClickListener() {

            try {
                val B5 = input1.text.toString().toDouble()
                val C5 = input2.text.toString().toDouble()
                val D5 = input3.text.toString().toDouble()
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
                        (Math.cos(Math.toRadians(D5) - Math.toRadians(B5)) * Math.cos(Math.toRadians(C5)) - 0.15127) / (Math.sin(
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
                        } //Append(getText(Az.toString()), getText(Az.toString())
                    }else -> println("")
                }
            }catch (e:Exception){
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
                output1.text=""
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











