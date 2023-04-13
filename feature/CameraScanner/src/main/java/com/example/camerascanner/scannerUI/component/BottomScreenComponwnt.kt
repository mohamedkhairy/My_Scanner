package com.example.camerascanner.scannerUI

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.camerascanner.R
import com.example.camerascanner.scannerUI.model.OptionsData


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomScreen() {
//    val scope = rememberCoroutineScope()

//    val optionsDataList = listOf<OptionsData>(
//        OptionsData(R.drawable.empty_basket, "Handla", 60.dp, false),
//        OptionsData(R.drawable.option2, "Hållberhetsdeklaration", 60.dp, false)
//    )
//    var indexRemember by remember { mutableStateOf(0) }

//    val isNeedExpansion = rememberSaveable{ mutableStateOf(false) }

//    val odl by  mutableStateOf(optionsDataList)




//    val animatedSizeDp: Dp by animateDpAsState(
//        targetValue =
//        if (isNeedExpansion.value)  80.dp
//        else  60.dp
//    )



    val odl =
        remember {
        mutableStateListOf<OptionsData>(
            OptionsData(R.drawable.empty_basket, "Handla", 60.dp, false),
            OptionsData(R.drawable.option2, "Hållberhetsdeklaration", 60.dp, false)
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
            itemsIndexed(items = odl) { index, item ->
                Column(
                    Modifier.requiredHeight(90.dp).padding(8.dp).align(Center),
                    verticalArrangement = Arrangement.Center

                ) {
                    CardIcon(odl[index].sizeDp, item.icon){

                        item.apply {

                            if (isExpanded){
                                odl[index] = copy(sizeDp = 60.dp, isExpanded =  false)
//                                sizeDp = 60.dp
//                                isExpanded = false
                            }else {
                                odl[index] = copy(sizeDp = 80.dp, isExpanded =  true)

//                                sizeDp = 80.dp
//                                isExpanded = true
                            }
//                            val aCopy = this.copy()
//                            odl[index] = aCopy

                        }
//                        indexRemember = index
//                        isNeedExpansion.value = !isNeedExpansion.value



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
        backgroundColor = Color.White,
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



//@Composable
//private fun AnimateDpAsState() {
//    val isNeedExpansion = rememberSaveable{ mutableStateOf(false) }
//
//    val animatedSizeDp: Dp by animateDpAsState(targetValue = if (isNeedExpansion.value) 200.dp else 100.dp)
//
//    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//        CircleImage(animatedSizeDp)
//        Button(
//            onClick = {
//                isNeedExpansion.value = !isNeedExpansion.value
//            },
//            modifier = Modifier
//                .padding(top = 50.dp)
//                .width(300.dp)
//        ) {
//            Text(text = "animateDpAsState")
//        }
//    }
//}
//
//@Composable
//fun CircleImage(imageSize: Dp) {
//    val animatableSize = remember { Animatable(Size.Zero, Size.VectorConverter) }
//    val scope = rememberCoroutineScope()
//
//    Image(
//        painter = painterResource(R.drawable.scan_new),
//        contentDescription = "Circle Image",
//        contentScale = ContentScale.Crop,
//        modifier = Modifier
//            .size(imageSize)
//            .onSizeChanged {
//                Log.d("xxx" , "0000")
//                Log.d("xxx" , "${it}")
//
//                scope.launch {
//                    animatableSize.snapTo(it.toSize())
//                }
//            }
//            .clip(CircleShape)
//            .border(5.dp, Color.Gray, CircleShape)
//    )
//}



@Preview
@Composable
fun ComposableCardPreview() {
    BottomScreen()
}

