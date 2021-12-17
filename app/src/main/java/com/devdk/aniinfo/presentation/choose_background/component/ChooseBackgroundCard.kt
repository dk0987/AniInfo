package com.devdk.aniinfo.presentation.choose_background.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun ChooseBackgroundCard(
    color : Color,
    elevation : Dp = 10.dp,
    roundedCornerShape: Dp = 15.dp,
    onClick : () -> Unit
) {
    Card(
        onClick = onClick ,
        elevation = elevation ,
        shape = RoundedCornerShape(roundedCornerShape),
        modifier = Modifier
            .size(width = 180.dp , height = 280.dp)
            .padding(10.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color))
    }
}