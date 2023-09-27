# Azimuth and Inverted Elevation calculator to point the satellite (pointing calculator)


pointing calculator used to calculate the pointing angle of satellite by using local location

by cloning or downloading the zip file you can see the app code in android studio by importing the project 

the apk file of this project is available in the folder and zipped file from the 'pointingCalc-mukee' folder
The application is calculate the azimuth and elevations angle of the satellite by using the site latitude and longitude and satellite longtude
# Azimuth and Elevations calculator
Azimuth angle means the angle between North, measured clockwise around the observer's horizon, and a celestial body (sun, moon). It can measured using a compass 

Elevation angle is the vertical angle formed between the direction of travel of electromagnetic wave radiated from an earth station antenna pointing directly towards a satellite and horizontal plane.

Inverted elevation is an elevation angle when the dish is inverted in upward direction.

This app is developed by considering a Circular polarized antennas it may be left-hand (LHCP) or right-hand (RHCP). so the polarization angle is considered not important.

Finally, if your satellite are in the west direction you can use the minus sign ex. if the satellite is at 18°W you can use '-18°' or 360-18=342.


Tip: Typical satellite antenna offsets are 22.3º and 17.3° (i.e., Calculated Elevation – Offset = Measured Elevation angle). in this project the 22.3 is used as an offset of the antenna if your antenna have different offset angle from 22.3 you must add the difference of the offset antenna on the elevation 
