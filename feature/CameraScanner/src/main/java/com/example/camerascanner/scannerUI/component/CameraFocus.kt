package com.example.camerascanner.scannerUI.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.camerascanner.R

@Composable
fun CameraFocus(){
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Center) {
        Pulsating() {

            Image(
                painter = painterResource(id = R.drawable.cam_border),
                contentDescription = "borders",
                modifier = Modifier
                    .size(200.dp, 200.dp)
                    .padding(8.dp)
            )

        }
            Image(
                painter = painterResource(id = R.drawable.barcode),
                contentDescription = "Barcode",
                modifier = Modifier
                    .size(150.dp, 200.dp)
                    .padding(8.dp)

            )

    }
}