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

1. Clone the repository:
