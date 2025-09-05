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

### Option 1: Install APK
1. Download the APK from [here](https://github.com/Mubarekethio/satellite-pointing-Angle-Calculator/releases/download/V1.12/sat-pointing-calculator.apk).  
2. Open the APK file on your Android device to install the app.

### Option 2: Build from Source
  1. **Clone the repository** :
       -    ```bash
            git clone https://github.com/Mubarekethio/satellite-pointing-Angle-Calculator.git
  2. Open the project in Android Studio.
  3. Build and run the app on a physical device or emulator (API level 21+ recommended).


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


## License
Licensed under the **MIT License** – see [LICENSE](LICENSE)  

---

## Author
👤 **Mu Kee** – [GitHub Profile](https://github.com/Mubarekethio)  



