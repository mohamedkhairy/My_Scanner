package com.example.camerascanner.scannerUI.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.camerascanner.R
import com.example.camerascanner.scannerUI.core.OptionsStates
import com.example.camerascanner.scannerUI.theme.light_green


@Composable
fun BottomScreen() {


    val optionsStatesList =
        remember {
        mutableStateListOf<OptionsStates>(
            OptionsStates(R.drawable.empty_basket, "Handla", 80.dp, true),
            OptionsStates(R.drawable.option2, "Hållberhetsdeklaration", 60.dp, false)
        )
    }


    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
            .requiredHeight(200.dp),
        contentAlignment = Center

    ) {




        LazyRow(modifier = Modifier.align(Center)) {
            itemsIndexed(items = optionsStatesList) { index, item ->
                Column(
                    Modifier.requiredHeight(90.dp).padding(8.dp).align(Center),
                    verticalArrangement = Arrangement.Center

                ) {
                    CardIcon(optionsStatesList[index].sizeDp, item.icon){

                            item.apply {

                                if (isExpanded) {
                                    optionsStatesList[index] = copy(sizeDp = 60.dp, isExpanded = false)
                                } else {
                                    optionsStatesList[index] = copy(sizeDp = 80.dp, isExpanded = true)
                                }
                            }

                    }
                }
            }
        }



    }
}


@Composable
fun CardIcon(dpSize: Dp , imageIcon: Int, onClick:() -> Unit){


    Card(modifier = Modifier
        .size(dpSize)
        .clickable { onClick() },
        backgroundColor = light_green,
        elevation = 12.dp
    )
    {

        Image(
            painter = painterResource(id = imageIcon),
            contentDescription = "search",
            modifier = Modifier
                .size(24.dp)
                .padding(12.dp)
        )

    }
}




@Preview
@Composable
fun ComposableCardPreview() {
    BottomScreen()
}

