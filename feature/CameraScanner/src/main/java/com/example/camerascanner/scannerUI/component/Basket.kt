package com.example.camerascanner.scannerUI.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BasketWithBadge(){

    BadgedBox(
        badge = {
            Badge(
                backgroundColor = Color.Green
            )
            { Text("5") }
        },
    ) {
        Card(

            backgroundColor = Color.White,
            elevation = 12.dp,
        )
        {
            Text(
                text = "Till varukorg",
                color = Color.DarkGray,
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@Preview
@Composable
fun ComposableBadgePreview() {

    BasketWithBadge()
}