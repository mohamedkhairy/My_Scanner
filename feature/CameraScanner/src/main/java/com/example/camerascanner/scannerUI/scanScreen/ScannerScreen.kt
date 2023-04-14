package com.example.camerascanner.scannerUI.scanScreen


import android.Manifest
import android.app.Activity
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.domain.navigation.Screen
import com.example.camerascanner.R
import com.example.camerascanner.scannerUI.BarCodeAnalyser
import com.example.camerascanner.scannerUI.component.BottomScreen
import com.example.camerascanner.scannerUI.component.ProductDetailsCard
import com.example.camerascanner.scannerUI.component.BasketWithBadge
import com.example.camerascanner.scannerUI.component.CameraFocus
import com.example.camerascanner.scannerUI.component.HeaderScreen
import com.example.camerascanner.scannerUI.core.ProductInformation
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.delay
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScannerScreen(navController: NavController) {


    val screenViewModel: ScannerScreenViewModel = hiltViewModel()
    val act = (LocalContext.current as Activity)
    val view = LocalView.current
    val systemUiController = rememberSystemUiController()
    val basketCountRemember = remember { mutableStateOf(0) }


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
        val basketCount = screenViewModel.basketCount.value

            Box {



                CameraPreview(screenViewModel)
                Column {

                HeaderScreen{navController.popBackStack(Screen.Handla.route , false)}

                   Column (
                       verticalArrangement = Arrangement.Center,
                       modifier = Modifier
                           .fillMaxWidth()
                           .fillMaxHeight(0.6f)
                   ){
                       CameraFocus()
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
                                    var count = basketCountRemember.value
                                    basketCountRemember.value = ++count
                                    screenViewModel.setBasketCount(count)

                                    delay(2000)


                                    screenViewModel.setIsNewProduct(false)

                                }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 16.dp, bottom = 8.dp),
                        horizontalArrangement = Arrangement.End) {
                            BasketWithBadge(basketCount)
                        }
                        BottomScreen()
                    }

                }


            }


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
                            screenViewModel.setIsNewProduct(true)                        }
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
                    Log.d("Scanner", "CameraPreview: ${e.localizedMessage}")
                }
            }, ContextCompat.getMainExecutor(context))
        }
    )

}



