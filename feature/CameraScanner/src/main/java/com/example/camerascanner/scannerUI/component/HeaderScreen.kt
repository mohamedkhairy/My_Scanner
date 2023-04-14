package com.example.camerascanner.scannerUI.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camerascanner.R

@Composable
fun HeaderScreen(onClose: () -> Unit){
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
            .requiredHeight(100.dp),

        ) {



        Text( modifier = Modifier
            .background(Color.Transparent)
            .align(Center)
            ,
            text = "Handla Online",
            fontSize = 18.sp,
            color = Color.White,
        )


        Image(
            painter = painterResource(id = R.drawable.close),
            contentDescription = "Close",
            modifier = Modifier
                .size(30.dp)
                .padding(8.dp)
                .align(CenterEnd)
                .clickable { onClose() }

        )



    }
}

