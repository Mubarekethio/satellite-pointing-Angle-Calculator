package org.mukee.calculatorapp.pointingcalc_mukee


class PointingClass(val site_long: Double, val site_lat: Double, val sat_long: Double) {


    var Azimuth1: Double = Math.toDegrees(
        Math.atan(
            0 - (Math.tan(Math.toRadians(sat_long) - Math.toRadians(site_long)) / (Math.sin(
                Math.toRadians(
                    site_lat
                )
            )))
        )
    ) + 180
    //longsite+latsite+longSat
    var normal_El1: Double = Math.toDegrees(
        Math.atan(
            (Math.cos(Math.toRadians(sat_long) - Math.toRadians(site_long)) * Math.cos(
                Math.toRadians(
                    site_lat
                )
            ) - 0.15127) / (Math.sin(
                Math.acos(
                    Math.cos(Math.toRadians(sat_long) - Math.toRadians(site_long)) * Math.cos(
                        Math.toRadians(site_lat)
                    )
                )
            ))
        )
    )
    var inverted_El1 : Double= 90 - (normal_El1+ 22.3)


    val Azimuth= String.format("%.4f", Azimuth1)
    val normal_El= String.format("%.4f", normal_El1)
    val inverted_El= String.format("%.4f", inverted_El1)





}

