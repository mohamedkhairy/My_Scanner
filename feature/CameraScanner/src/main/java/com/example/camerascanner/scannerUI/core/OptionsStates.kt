package com.example.camerascanner.scannerUI.core

import androidx.compose.ui.unit.Dp

data class OptionsStates (
    val icon: Int,
    val title: String,
    var sizeDp: Dp ,
    var isExpanded: Boolean = false

    )