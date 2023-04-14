package com.example.handla.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.core.domain.navigation.Screen
import com.example.handla.R

@Composable
fun HandlaScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(8.dp)


    ) {





        Box (modifier = Modifier.fillMaxWidth()){

            Text(
                modifier = Modifier.padding(8.dp),
                text = "Handla",
                color = Color.DarkGray,
                style = MaterialTheme.typography.h6,
                fontSize = 22.sp,
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Row(modifier = Modifier.align(CenterEnd).padding(8.dp)) {


                Image(
                    painter = painterResource(id = R.drawable.search_new),
                    contentDescription = "search",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(8.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.scan_new),
                    contentDescription = "camera scanner",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(8.dp)
                        .clickable {
                            navController.navigate(Screen.Scanner.route)
                        }

                )
            }
        }

    }
}


@Preview
@Composable
fun ComposablePreview() {
//    HandlaScreen()
}