# PointingCalcMukee

**PointingCalcMukee** is an Android application designed to assist engineers and satellite enthusiasts in calculating **satellite antenna pointing parameters** for geostationary satellites. The application integrates **geolocation**, **trigonometric modeling**, and **antenna physics** to provide accurate **azimuth**, **elevation**, **inclinometer readings**, and **obstacle clearance distances**.  

This tool is particularly useful for **optical communication, VSAT, and satellite link alignment** in the field.

---

## Overview

Aligning a satellite antenna requires precise calculation of **azimuth** (horizontal angle), **elevation** (vertical angle), and consideration of **dish offsets** and **obstacles** along the line of sight. **PointingCalcMukee** automates this process by:

- Fetching the user’s **current geographic coordinates** using GPS.  
- Calculating satellite pointing parameters based on site coordinates and selected satellite position.  
- Accounting for **antenna offset angles** and **inclinometer readings**.  
- Estimating **obstacle clearance distances**, ensuring safe line-of-sight alignment.  

---

## Features

### Satellite Pointing Calculation
- **Azimuth** – Computes the horizontal angle of the antenna relative to true north.  
- **Elevation** – Computes the vertical angle of the antenna above the horizon.  
- **Inclinometer readings** – Calculates tilt angles for different dish configurations:  
  - Center feed  
  - Normal dish  
  - Inverted dish  
- **Obstacle clearance** – Provides distances for 0°, 5°, and 10° safety margins to avoid obstructions.  

### GPS Integration
- Automatically fetches **longitude and latitude** from the device.  
- Supports fallback to manual input if GPS is unavailable.  
- Uses **FusedLocationProviderClient** for high accuracy.  

### User Interface
- **Satellite dropdown** – Predefined list of geostationary satellites with orbital positions.  
- **Floating action buttons** – Intuitive buttons for calculating, clearing, and fetching location.  
- Real-time feedback via **TextViews** for calculation results.  

---

## Methodology

PointingCalcMukee uses **geometric and trigonometric principles** to determine satellite pointing:

1. **Azimuth Calculation**  
   The horizontal angle between true north and the satellite, considering:  
   - Difference between satellite longitude and site longitude  
   - Latitude of the site  
   - Conversion from radians to degrees  

2. **Elevation Calculation**  
   Computes the vertical angle using:  
   - Site coordinates  
   - Satellite longitude  
   - Earth curvature approximation  
   - Offset correction for the dish  

3. **Inclinometer and Offset Adjustments**  
   - Computes tilt angles for center feed, normal, and inverted dish configurations  
   - Ensures alignment consistency with inclinometer readings  

4. **Obstacle Clearance**  
   - Estimates safe distances based on antenna elevation, obstacle height, and safety margins (0°, 5°, 10°)  

---

## Installation

### Option 1: Install APK
1. Download the APK from [here](https://github.com/Mubarekethio/satellite-pointing-Angle-Calculator/releases/download/V1.12/sat-pointing-calculator.apk).  
2. Open the APK file on your Android device to install the app.  

### Option 2: Build from Source
1. Clone the repository:  
   git clone https://github.com/Mubarekethio/satellite-pointing-Angle-Calculator.git
   
3. Open the project in **Android Studio**.
4. Build and run the app on a physical device or emulator (**API level 21+ recommended**).  

---

## Permissions

The app requires:

- `ACCESS_FINE_LOCATION` – Precise GPS coordinates for site location  
- `ACCESS_COARSE_LOCATION` – Approximate location  
- `INTERNET` – Optional for future enhancements  

> Background location access is **not required**, as the app only fetches location on demand.

---

## Usage

1. Open the app on your device.  
2. Enter your site longitude and latitude, or use the **GPS button** to auto-fill.  
3. Select a satellite from the **dropdown menu**.  
4. Optionally enter:  
   - Offset angle (default: **22.3°**)  
   - Obstacle height (default: **0 m**)  
5. Click **Calculate** to obtain:  
   - Azimuth and elevation  
   - Inclinometer readings  
   - Obstacle clearance distances  
6. Click **Clear** to reset inputs.  

---

## Technical Architecture

- `PointingClass.kt` – Core calculation logic for azimuth, elevation, inclinometer, and obstacle clearance  
- `MainActivity.kt` – Handles UI, input validation, GPS integration, and triggers calculations  
- `activity_main.xml` – Layout defining input fields, buttons, dropdowns, and result views  
- `AndroidManifest.xml` – Declares required permissions and main activity  

---

## Future Enhancements

- Integration with **real-time satellite ephemeris data** for dynamic orbital positions  
- Support for **non-geostationary satellites** and mobile tracking  
- Graphical **azimuth/elevation visualization** for easier field alignment  
- Optional **cloud syncing** to log and track multiple site installations  

---

## License

This project is licensed under the **MIT License**.  
See the [LICENSE](LICENSE) file for full details.  

---

## Author

**Mu Kee** – [GitHub Profile](https://github.com/Mubarekethio)
   





























# PointingCalcMukee

PointingCalcMukee is an Android application designed to assist engineers and satellite enthusiasts in calculating **satellite antenna pointing parameters** for geostationary satellites. The application integrates **geolocation**, **trigonometric modeling**, and **antenna physics** to provide accurate azimuth, elevation, inclinometer readings, and obstacle clearance distances.  

This tool is particularly useful for optical communication, VSAT, and satellite link alignment in the field.

---

## Overview

Aligning a satellite antenna requires precise calculation of **azimuth** (horizontal angle), **elevation** (vertical angle), and consideration of **dish offsets** and **obstacles** along the line of sight. PointingCalcMukee automates this process by:

- Fetching the user’s **current geographic coordinates** using GPS.
- Calculating satellite pointing parameters based on site coordinates and selected satellite position.
- Accounting for **antenna offset angles** and **inclinometer readings**.
- Estimating **obstacle clearance distances**, ensuring safe line-of-sight alignment.

---

## Features

### 1. Satellite Pointing Calculation
- **Azimuth** – Computes the horizontal angle of the antenna relative to true north.
- **Elevation** – Computes the vertical angle of the antenna above the horizon.
- **Inclinometer readings** – Calculates tilt angles for different dish configurations:
  - Center feed
  - Normal dish
  - Inverted dish
- **Obstacle clearance** – Provides distances for 0°, 5°, and 10° safety margins to avoid physical obstructions.

### 2. GPS Integration
- Automatically fetches **longitude and latitude** from the device.
- Supports fallback to manual input if GPS is unavailable.
- Uses **FusedLocationProviderClient** for high accuracy.

### 3. User Interface
- **Satellite dropdown** – Predefined list of geostationary satellites with their orbital positions.
- **Floating action buttons** – Intuitive buttons for calculating, clearing, and fetching location.
- Real-time feedback via **TextViews** for calculation results.

---

## Methodology

PointingCalcMukee uses **geometric and trigonometric principles** to determine satellite pointing:

1. **Azimuth Calculation**:  
   The horizontal angle between true north and the satellite is computed differently depending on the **hemisphere**. The formula considers:
   - Difference between satellite longitude and site longitude.
   - Latitude of the site.
   - Conversion from radians to degrees.

2. **Elevation Calculation**:  
   The elevation angle is computed based on:
   - Site coordinates
   - Satellite longitude
   - Earth curvature approximation
   - Offset correction (dish angle offsets)

3. **Inclinometer and Offset Adjustments**:  
   - Computes tilt angles for center feed, normal, and inverted dish configurations.
   - Ensures alignment consistency with inclinometer readings in field installations.

4. **Obstacle Clearance**:  
   - Uses basic trigonometry to estimate clearance distances based on:
     - Antenna elevation
     - Obstacle height
     - Safety margins (0°, 5°, 10°)

---


 ## Installation

### Option 1: Install APK
1. Download the APK from [here](https://github.com/Mubarekethio/satellite-pointing-Angle-Calculator/releases/download/V1.12/sat-pointing-calculator.apk).  
2. Open the APK file on your Android device to install the app.

### Option 2: Build from Source
  1. **Clone the repository** :
       -    ```bash
            git clone https://github.com/Mubarekethio/satellite-pointing-Angle-Calculator.git
  3. Open the project in Android Studio.
  4. Build and run the app on a physical device or emulator (API level 21+ recommended).

## Permissions

**The app requires:**:  
   - ACCESS_FINE_LOCATION – Precise GPS coordinates for site location.
   - ACCESS_COARSE_LOCATION – Approximate location.
   - INTERNET – Optional for future enhancements.
   - Background location access is not required as the app only fetches location on demand.
     
## Usage:
1. Open the app on your device.
2. Enter your site longitude and latitude, or click the GPS button to auto-fill.
3. Select a satellite from the dropdown menu.
4. **Optionally enter**:
    - Offset angle (default: 22.3°)
    - Obstacle height (default: 0 m)

5. **Click calculate to obtain:**
  - Azimuth and elevation
  - Inclinometer readings
  - Obstacle clearance distances
    
6. Click clear to reset inputs.

## Technical Architecture

   - PointingClass.kt – Core calculation logic for azimuth, elevation, inclinometer, and obstacle clearance.
   - MainActivity.kt – Handles user interface, input validation, GPS integration, and triggers calculation.
   - activity_main.xml – Layout defining input fields, buttons, dropdowns, and result views.
   - AndroidManifest.xml – Declares required permissions and main activity.

## Future Enhancements

  - Integration with real-time satellite ephemeris data for dynamic orbital positions.
  - Support for non-geostationary satellites and mobile tracking.
  - Graphical azimuth/elevation visualization for easier field alignment.
  - Optional cloud syncing to log and track multiple site installations.

## License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for full details.

---

## Author

**Mu Kee** – [GitHub Profile](https://github.com/Mubarekethio)



