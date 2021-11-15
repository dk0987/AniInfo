package com.devdk.aniinfo.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalMaterialApi
@Composable
fun AnimeCard(
    painter: Painter,
    corner : Dp = 10.dp,
    elevation : Dp = 10.dp,
    title : String = "",
    Onclick : () -> Unit  = {},
) {
    Card(
        onClick = Onclick ,
        shape = RoundedCornerShape(corner),
        elevation = elevation,
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
            .padding(10.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painter,
                contentDescription = "Anime",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent , Color.Black
                        )
                    )),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }

    }

}