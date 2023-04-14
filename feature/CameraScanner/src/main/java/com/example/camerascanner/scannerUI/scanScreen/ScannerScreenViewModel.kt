package com.example.camerascanner.scannerUI.scanScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.camerascanner.scannerUI.core.ProductInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScannerScreenViewModel @Inject constructor() :
    ViewModel() {

    val productInformation: MutableState<ProductInformation?> = mutableStateOf(null)
    val isNewProduct: MutableState<Boolean> = mutableStateOf(true)
    val basketCount: MutableState<Int> = mutableStateOf(0)




    fun setProductInformation(product: ProductInformation?){
        productInformation.value = product
    }

    fun setIsNewProduct(value: Boolean){
        isNewProduct.value = value
    }

    fun setBasketCount(count: Int){
            basketCount.value = count
    }

}