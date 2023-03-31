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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.slice_maps.ui.theme.Slice_MapsTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.example.slice_maps.MapStyle
import com.google.android.gms.maps.model.MapStyleOptions


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
    val bcit = LatLng(49.283451, -123.115251)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(bcit, 14f)
    }
//    val context = LocalContext.current
//    val placesClient = remember(context) {
//        Places.createClient(context)
//    }
//    val places = remember { mutableStateOf<List<Place>>(emptyList()) }
//    LaunchedEffect(Unit) {
//        val request = FindCurrentPlaceRequest.newInstance(listOf(Place.Field.ID, Place.Field.NAME))
//        val response = placesClient.findCurrentPlace(request)
//        places.value = response.placeLikelihoods.map { it.place }
//    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(
            zoomControlsEnabled = true,
            compassEnabled = true,
            myLocationButtonEnabled = false,
            mapToolbarEnabled = false,
            scrollGesturesEnabled = true,
            zoomGesturesEnabled = true,
            tiltGesturesEnabled = false,
            rotationGesturesEnabled = true
        ),
            properties = MapProperties(
            mapStyleOptions = MapStyleOptions(
                MapStyle.json
            )

        )

    ) {
        Marker(
            state = MarkerState(position = bcit),
            title = "BCIT",
            snippet = "Downtown Campus"
        )
        Circle(
            center = bcit,
            radius = 500.0, // radius in meters
            fillColor = Color(0x462196F3), // semi-transparent red color
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