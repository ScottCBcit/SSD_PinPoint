package com.example.slice_maps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.slice_maps.ui.theme.Slice_MapsTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Slice_MapsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SliceMap()
                }
            }
        }
    }
}

@Composable
fun SliceMap() {
    val location = LatLng(49.283451, -123.115251)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 14f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(
            myLocationButtonEnabled = false,
            mapToolbarEnabled = false,
        ),
        properties = MapProperties(
            mapStyleOptions = MapStyleOptions(
                MapStyle.json
            )
        ),

    ) {
        Marker(
            state = MarkerState(position = location),
            title = "BCIT",
            snippet = "Downtown Campus"
        )
        Circle(
            center = location,
            radius = 500.0, // radius in meters
            fillColor = Color(0x462196F3), // semi-transparent blue color
            strokeWidth = 0f // no stroke
        )
    }
}






@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Slice_MapsTheme {
        SliceMap()
    }
}