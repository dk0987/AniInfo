package com.devdk.aniinfo.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Banner(
    painter: Painter,
    modifier: Modifier = Modifier,
    cornerShape: Dp = 20.dp,
    elevation : Dp = 20.dp,
    Onclick : () -> Unit = {},
    isBanner : Boolean,
) {

    Card(
        onClick = Onclick,
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp),
        elevation = elevation,
        shape = RoundedCornerShape(cornerShape),
    ) {
        if (!isBanner){
            Box(modifier = Modifier
                .fillMaxSize()
            ){
                Image(
                    painter = painter,
                    contentDescription = "Banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    alpha = 0.5f
                )
            }
        }
        Box(modifier = Modifier
            .fillMaxSize()
        ){
            Image(
                painter = painter,
                contentDescription = "Banner",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}






















