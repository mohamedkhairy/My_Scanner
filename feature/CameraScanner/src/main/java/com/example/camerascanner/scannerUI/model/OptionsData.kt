package com.example.camerascanner.scannerUI.model

import androidx.compose.ui.unit.Dp

data class OptionsData (
    val icon: Int,
    val title: String,
    var sizeDp: Dp ,
    var isExpanded: Boolean = false

    )