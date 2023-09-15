package org.mukee.calculatorapp.pointingcalc_mukee

class pointingClass (val B5: Double, val C5: Double, val D5: Double) {

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

}

