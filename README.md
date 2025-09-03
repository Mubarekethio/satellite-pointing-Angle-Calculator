# PointingCalcMukee 

[![Android](https://img.shields.io/badge/Platform-Android-green?logo=android)](https://developer.android.com/)  
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue?logo=kotlin)](https://kotlinlang.org/)  
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)  
[![Release](https://img.shields.io/github/v/release/Mubarekethio/satellite-pointing-Angle-Calculator)](https://github.com/Mubarekethio/satellite-pointing-Angle-Calculator/releases)  

**PointingCalcMukee** is an Android application designed to assist engineers and satellite enthusiasts in calculating **satellite antenna pointing parameters** for geostationary satellites. It integrates **geolocation**, **trigonometric modeling**, and **antenna physics** to provide accurate **azimuth**, **elevation**, **inclinometer readings**, and **obstacle clearance distances**. This tool is particularly useful for **optical communication, VSAT, and satellite link alignment** in the field.  

---

## 📑 Table of Contents
1. [Overview](#overview)  
2. [Features](#features)  
3. [Methodology](#methodology)  
4. [Installation](#installation)  
5. [Permissions](#permissions)  
6. [Usage](#usage)  
7. [Technical Architecture](#technical-architecture)  
8. [Future Enhancements](#future-enhancements)  
9. [License](#license)  
10. [Author](#author)  

---

## Overview
Aligning a satellite antenna requires precise calculation of **azimuth** (horizontal angle), **elevation** (vertical angle), and consideration of **dish offsets** and **obstacles** along the line of sight. **PointingCalcMukee** automates this process by:  

- Fetching the user’s **current geographic coordinates** using GPS  
- Calculating pointing parameters based on site coordinates and satellite position  
- Accounting for **antenna offset angles** and **inclinometer readings**  
- Estimating **obstacle clearance distances** to ensure safe alignment  

---

## Features

### Satellite Pointing Calculation
- **Azimuth** – Computes the horizontal angle of the antenna relative to true north  
- **Elevation** – Computes the vertical angle above the horizon  
- **Inclinometer readings** – Tilt angles for different dish configurations:  
  - Center feed  
  - Normal dish  
  - Inverted dish  
- **Obstacle clearance** – Provides distances for 0°, 5°, and 10° safety margins  

### GPS Integration
- Fetches **longitude/latitude** via device GPS  
- Supports **manual input** if GPS is unavailable  
- Uses **FusedLocationProviderClient** for high accuracy  

### User Interface
- **Satellite dropdown** with predefined geostationary satellites  
- **Floating action buttons** for calculate, clear, and GPS fetch  
- Real-time results displayed via **TextViews**  

---

## Methodology
PointingCalcMukee uses **geometric and trigonometric principles**:  

1. **Azimuth Calculation** – Based on site longitude/latitude and satellite longitude  
2. **Elevation Calculation** – Considers site coordinates, Earth curvature, and dish offset  
3. **Inclinometer Adjustments** – For center feed, normal, and inverted dish setups  
4. **Obstacle Clearance** – Estimates safe distances for different elevation margins  

---

## Installation

### Install APK
1. Download the APK from [here](https://github.com/Mubarekethio/satellite-pointing-Angle-Calculator/releases/download/V1.12/sat-pointing-calculator.apk)  
2. Open the APK on your Android device to install  

### Permissions
- `ACCESS_FINE_LOCATION` – Precise GPS coordinates  
- `ACCESS_COARSE_LOCATION` – Approximate GPS  
- `INTERNET` – Optional for future enhancements  

> Background location is not required, only fetched on demand  

### Usage
1. Open the app  
2. Enter site longitude/latitude or auto-fill with GPS  
3. Select a satellite from the dropdown (Optional)  
4. Enter offset angle (default: 22.3°) and obstacle height (default: 0 m)  
5. Tap **Calculate** to see:  
   - Azimuth & Elevation  
   - Inclinometer readings  
   - Obstacle clearance distances  
6. Tap **Clear** to reset inputs  

---

## Technical Architecture
- **PointingClass.kt** – Core calculation logic  
- **MainActivity.kt** – UI, GPS integration, validation, calculations  
- **activity_main.xml** – Input fields, dropdowns, buttons, and results layout  
- **AndroidManifest.xml** – Permissions and app declaration  

---

## Future Enhancements
- Real-time satellite ephemeris integration  
- Support for non-geostationary satellites  
- Graphical visualization of azimuth/elevation  
- Cloud syncing for multiple site installations  

---

## 📐 Azimuth & Elevation Diagram

![Azimuth and Elevation](data:image/svg+xml;utf8,
<svg xmlns="http://www.w3.org/2000/svg" width="300" height="200" viewBox="0 0 300 200">
  <!-- Horizon -->
  <line x1="20" y1="180" x2="280" y2="180" stroke="#555" stroke-width="2"/>
  <text x="10" y="185" font-family="Arial" font-size="12">Horizon</text>

  <!-- Satellite direction -->
  <line x1="150" y1="180" x2="250" y2="50" stroke="#1f77b4" stroke-width="3"/>
  <circle cx="250" cy="50" r="5" fill="#1f77b4"/>
  <text x="255" y="50" font-family="Arial" font-size="12">Satellite</text>

  <!-- Azimuth arc -->
  <path d="M150,180 A50,50 0 0,1 220,180" fill="none" stroke="#ff7f0e" stroke-width="2"/>
  <text x="180" y="170" font-family="Arial" font-size="12" fill="#ff7f0e">Azimuth</text>

  <!-- Elevation arc -->
  <path d="M150,180 A70,70 0 0,0 220,80" fill="none" stroke="#2ca02c" stroke-width="2"/>
  <text x="160" y="120" font-family="Arial" font-size="12" fill="#2ca02c">Elevation</text>
</svg>
)


---

## License
Licensed under the **MIT License** – see [LICENSE](LICENSE)  

---

## Author
👤 **Mu Kee** – [GitHub Profile](https://github.com/Mubarekethio)  








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



