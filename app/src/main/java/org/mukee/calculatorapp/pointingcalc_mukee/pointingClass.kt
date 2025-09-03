package org.mukee.calculatorapp.pointingcalc_mukee

import kotlin.math.acos
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan


class PointingClass(
    val site_long: Double, // Site longitude
    val site_lat: Double,  // Site latitude
    val sat_long: Double, // Satellite longitude
    val offsetA: Double,  // Offset angle
    val height: Double    // Height of the obstacle
) {

    //Northern Hemispher Azimuth
    val azNorthern: Double = Math.toDegrees(
        atan(
            x = 0 - (tan(x = Math.toRadians(sat_long) - Math.toRadians(site_long)) / (sin(
                x = Math.toRadians(
                    site_lat
                )
            )))
        )
    ) + 180

    //souther hemispher Azimuth
    val azSouthern: Double=Math.toDegrees(atan(0-(tan(Math.toRadians(sat_long)-Math.toRadians(site_long))/(sin(Math.toRadians(site_lat))))))

    //longsite+latsite+longSat
    val elivation1: Double = Math.toDegrees(
        atan(
            x = ((cos(x = Math.toRadians(sat_long) - Math.toRadians(site_long)) * cos(
                x = Math.toRadians(
                    site_lat
                )
            )) - 0.15127) / (sin(
                acos(
                    x = cos(Math.toRadians(sat_long) - Math.toRadians(site_long)) * cos(
                        x = Math.toRadians(site_lat)
                    )
                )
            ))
        )
    )

    val azimuth1 = if (azNorthern>0) {
        azNorthern
    } else {
        azSouthern
    }




    //val azimuthN= String.format("%.4f", azNorthern)
    val azimuth= String.format("%.4f", azimuth1)

    val elevation= String.format("%.4f", elivation1)


    //Inclinometer reading  normal_El1

    val centr_El: String =String.format("%.4f", 90-elivation1.toDouble())
    val invert_El: String =String.format("%.4f", 90-(elivation1.toDouble()+offsetA.toDouble()))
    val normal_El: String =String.format("%.4f", 90-(elivation1.toDouble()-offsetA.toDouble()))


    //Obstacle clearance calc

    val noClear=String.format("%.3f", height/ tan(Math.toRadians(elivation1.toDouble())-Math.toRadians(0.0)))
    val minClear= String.format("%.3f", height/ tan(Math.toRadians(elivation1.toDouble())-Math.toRadians(5.0)))
    val recClear=String.format("%.3f", height/ tan(Math.toRadians(elivation1.toDouble())-Math.toRadians(10.0)))


}

