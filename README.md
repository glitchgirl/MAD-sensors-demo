// chatgpt is stupid and doesn't know how versions work.
Got it — here’s a **clean, correct, Compose-based version** of the project that matches what Android Studio actually gives you today.

No XML. No mismatches. This will work directly with your current template.

---

# 📱 Demo Project: Sensor Explorer (Jetpack Compose)

## 🎯 Goal

Build an app that:

* Detects all available sensors
* Displays them in a scrollable list (Compose UI)
* Shows key details for each sensor

---

# 🧱 Step 1: Create Project (IMPORTANT)

1. Open Android Studio
2. Click **New Project**
3. Select:

👉 **Empty Activity** (this is the Compose version you already used)

4. Keep defaults:

* Language: Kotlin ✅
* Minimum SDK: API 21+
* Compose: enabled (default)

---

# 📁 Step 2: Project Structure (what you should have)

You should already see:

* `MainActivity.kt`
* `ui.theme` package
* Compose imports like `@Composable`

👉 No XML files needed

---

# ⚙️ Step 3: Replace MainActivity Code

Replace EVERYTHING in `MainActivity.kt` with this:

```kotlin
package com.example.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Step 1: Get SensorManager
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Step 2: Get all sensors
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)

        // Step 3: Set Compose UI
        setContent {
            SensorApp(sensorList)
        }
    }
}

@Composable
fun SensorApp(sensors: List<Sensor>) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Sensor Explorer") })
        }
    ) { paddingValues ->

        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(sensors) { sensor ->
                SensorItem(sensor)
                Divider()
            }
        }
    }
}

@Composable
fun SensorItem(sensor: Sensor) {
    Text(
        text = """
            Name: ${sensor.name}
            Type: ${sensor.type}
            Vendor: ${sensor.vendor}
            Version: ${sensor.version}
            Max Range: ${sensor.maximumRange}
            Resolution: ${sensor.resolution}
            Power: ${sensor.power} mA
        """.trimIndent(),
        modifier = Modifier.padding(16.dp)
    )
}
```

---

# 🧠 Step 4: What This Matches from the Docs

This follows the same core API usage:

### ✅ Get SensorManager

```kotlin
getSystemService(Context.SENSOR_SERVICE)
```

### ✅ Get all sensors

```kotlin
sensorManager.getSensorList(Sensor.TYPE_ALL)
```

👉 These are straight from the official Android sensor overview

---

# 📊 Step 5: Run the App

### Best option:

* Run on a **real phone** (more sensors)

### Emulator:

* Will show limited sensors

---

# 🧪 Example Output

You’ll see a scrollable list like:

```
Name: Accelerometer
Type: 1
Vendor: Google
Max Range: 39.2
...

-------------------

Name: Gyroscope
...
```
