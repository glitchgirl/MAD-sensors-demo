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
import androidx.compose.ui.unit.dp

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

@OptIn(ExperimentalMaterial3Api::class)
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