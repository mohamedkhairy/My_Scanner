package com.example.camerascanner.scannerUI


import android.animation.TimeInterpolator
import android.view.animation.BounceInterpolator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camerascanner.R
import com.example.camerascanner.scannerUI.model.ProductInformation


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProductDetailsCard(show: Boolean, product: ProductInformation){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AnimatedVisibility(
            visible = show,
            enter = scaleIn(
                animationSpec = tween(
                    durationMillis = 500,
//                    easing = BounceInterpolator().toEasing()
                ),
            ),
            exit = scaleOut(animationSpec = tween(durationMillis = 500))
        ) {


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(90.dp)
                    .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp),
                backgroundColor = Color.White,
                elevation = 12.dp,
            )
            {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.path),
                        contentDescription = "checked",
                        modifier = Modifier
                            .size(25.dp, 25.dp)
                            .padding(start = 8.dp)
                    )

                    Image(
                        painter = painterResource(id = product.productImage),
                        contentDescription = "product image",
                        modifier = Modifier
                            .size(60.dp, 70.dp)
                            .padding(8.dp)
                    )

                    Text(
                        text = product.code,
                        color = Color.DarkGray,
                        fontSize = 18.sp
                    )


                }
            }
        }
    }
}

fun TimeInterpolator.toEasing() = Easing {
        x -> getInterpolation(x)
}

@Preview
@Composable
fun ComposableProductPreview() {
    val product = ProductInformation(R.drawable.product_ic, "12345")
    ProductDetailsCard(true, product)
}

