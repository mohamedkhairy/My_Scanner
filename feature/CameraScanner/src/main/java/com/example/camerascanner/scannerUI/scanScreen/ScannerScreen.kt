package com.example.camerascanner.scannerUI.scanScreen


import android.Manifest
import android.app.Activity
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.camerascanner.R
import com.example.camerascanner.scannerUI.BarCodeAnalyser
import com.example.camerascanner.scannerUI.BottomScreen
import com.example.camerascanner.scannerUI.ProductDetailsCard
import com.example.camerascanner.scannerUI.component.BasketWithBadge
import com.example.camerascanner.scannerUI.component.Pulsating
import com.example.camerascanner.scannerUI.model.ProductInformation
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.delay
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScannerScreen() {


//    WindowCompat.setDecorFitsSystemWindows((LocalContext.current as Activity).window, false)

    val screenViewModel: ScannerScreenViewModel = hiltViewModel()
    val act = (LocalContext.current as Activity)
    val view = LocalView.current
    val systemUiController = rememberSystemUiController()

    DisposableEffect(view) {
        WindowCompat.setDecorFitsSystemWindows(act.window, false)
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )

        onDispose {

            WindowCompat.setDecorFitsSystemWindows(act.window, true)
            systemUiController.setStatusBarColor(
                color = Color.White,
                darkIcons = true
            )
        }

    }
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    SideEffect {
        cameraPermissionState.launchPermissionRequest()
    }

    Surface(color = Color.Transparent) {
        val newProduct = screenViewModel.productInformation.value
        val isNewProduct = screenViewModel.isNewProduct.value

            Box {
//            Spacer(modifier = Modifier.height(10.dp))



                CameraPreview(screenViewModel)
                Column {

                HeaderScreen()
                   Column (
                       verticalArrangement = Arrangement.Center,
                       modifier = Modifier
                           .fillMaxWidth()
                           .fillMaxHeight(0.6f)
                   ){
                       bar()
                   }
                }

                Column(
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        newProduct?.let {
                            ProductDetailsCard(show = isNewProduct, product = it)
                            if (isNewProduct)
                                LaunchedEffect(key1 = Unit) {
                                    delay(2000)
                                    screenViewModel.setIsNewProduct(false)
//                                    screenViewModel.setProductInformation(null)
                                }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth().padding(end = 16.dp, bottom = 8.dp),
                        horizontalArrangement = Arrangement.End) {
                            BasketWithBadge()
                        }
                        BottomScreen()
                    }

                }


            }


    }


}


@Composable
fun HeaderScreen(){
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
                .clickable { }

        )



    }
}


@Composable
fun CameraPreview(screenViewModel: ScannerScreenViewModel) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var preview by remember { mutableStateOf<androidx.camera.core.Preview?>(null) }
    val barCodeVal = remember { mutableStateOf("") }
    val newProduct  by remember { mutableStateOf<ProductInformation?>(ProductInformation()) }



    AndroidView(
        factory = { AndroidViewContext ->
            PreviewView(AndroidViewContext).apply {
                this.scaleType = PreviewView.ScaleType.FILL_CENTER
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
            }
        },
        modifier = Modifier.fillMaxSize()
        ,
        update = { previewView ->
            val cameraSelector: CameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()
            val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
            val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
                ProcessCameraProvider.getInstance(context)

            cameraProviderFuture.addListener({
                preview = androidx.camera.core.Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                val barcodeAnalyser = BarCodeAnalyser { barcodes ->
                    barcodes.forEach { barcode ->

                        barcode.rawValue?.let { barcodeValue ->
                            barCodeVal.value = barcodeValue
                             val newProductCopy = newProduct?.copy(
                                 productImage = R.drawable.product_ic,
                                 code = barcodeValue
                             )
                            screenViewModel.setProductInformation(newProductCopy!!)
                            screenViewModel.setIsNewProduct(true)
                            Toast.makeText(context, barcodeValue, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                val imageAnalysis: ImageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(cameraExecutor, barcodeAnalyser)
                    }

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalysis
                    )
                } catch (e: Exception) {
                    Log.d("TAG", "CameraPreview: ${e.localizedMessage}")
                }
            }, ContextCompat.getMainExecutor(context))
        }
    )

}

@Composable
fun Screeb(){
    Surface(color = MaterialTheme.colors.background) {
        // Cards content
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(all = 16.dp),
                    backgroundColor = Color.Red,
                ) {
                    Text(text = "Card 1")
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(all = 16.dp),
                    backgroundColor = Color.Green,
                ) {
                    Text(text = "Card 2")
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(all = 16.dp),
                    backgroundColor = Color.Blue,
                ) {
                    Text(text = "Card 3")
                }
            }
            // Buttons content
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(text = "Button 1")
                }
                Button(
                    onClick = {},
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(text = "Button 3")
                }
                Button(
                    onClick = {},
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(text = "Button 2")
                }
            }
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    ScannerScreen()
//    Screeb()
}

@Composable
fun bar(){
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
                    .clickable { }

            )

        }

//        Column(
//            verticalArrangement = Arrangement.Center,
//        ) {
            Image(
                painter = painterResource(id = R.drawable.barcode),
                contentDescription = "Barcode",
                modifier = Modifier
                    .size(150.dp, 200.dp)
                    .padding(8.dp)

            )
//
//
//
//            Image(
//                painter = painterResource(id = R.drawable.cam_desc),
//                contentDescription = "Close",
//                modifier = Modifier
//                    .size(100.dp, 100.dp)
//                    .padding(8.dp)
//
//            )
//        }
    }
}


@Preview
@Composable
fun ComposableBarPreview() {
   bar()
}
