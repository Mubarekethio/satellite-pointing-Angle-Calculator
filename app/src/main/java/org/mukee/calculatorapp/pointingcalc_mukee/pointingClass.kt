package org.mukee.calculatorapp.pointingcalc_mukee


class PointingClass(val B5: Double, val C5: Double, val D5: Double) {


    //println(D5)
    val Az1: Double = Math.toDegrees(
        Math.atan(
            0 - (Math.tan(Math.toRadians(D5) - Math.toRadians(B5)) / (Math.sin(
                Math.toRadians(
                    C5
                )
            )))
        )
    ) + 180
    //longsite+latsite+longSat
    val El12: Double = Math.toDegrees(
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
    val El11: Double = 90 - (El12 + 22)
    val El1 = String.format("%.4f", El11)


    val Az= String.format("%.4f", Az1)
    val El= String.format("%.4f", El12)




}

