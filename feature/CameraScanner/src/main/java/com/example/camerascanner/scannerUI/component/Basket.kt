package com.example.camerascanner.scannerUI.component

import androidx.compose.foundation.layout.padding
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
import com.example.camerascanner.scannerUI.theme.ever_green
import com.example.camerascanner.scannerUI.theme.fern_green
import com.example.camerascanner.scannerUI.theme.light_green

@Composable
fun BasketWithBadge(count: Int) {

    BadgedBox(
        badge = {
            if (count != 0) {

                Badge(
                    backgroundColor = fern_green
                )
                {
                    Text(
                        text = "$count",
                        color = light_green
                    )

                }
            }
        },
    ) {
        Card(

            backgroundColor = Color.White,
            elevation = 12.dp,
        )
        {
            Text(
                text = "Till varukorg",
                color = ever_green,
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@Preview
@Composable
fun ComposableBadgePreview() {

    BasketWithBadge(1)
}