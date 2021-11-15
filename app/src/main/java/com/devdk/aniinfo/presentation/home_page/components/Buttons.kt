package com.devdk.aniinfo.presentation.home_page.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devdk.aniinfo.common.Size
import com.devdk.aniinfo.presentation.ui.theme.lightRed
import com.devdk.aniinfo.presentation.ui.theme.midRed

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Buttons(
    colors : List<Color> = listOf(lightRed , midRed),
    text : String ,
    OnClick : () -> Unit = {},
) {
    Card(
        onClick = OnClick,
        elevation = 20.dp,
        shape = RoundedCornerShape(size = Size.large),
        backgroundColor = lightRed,
        modifier = Modifier
            .padding(10.dp)
            .defaultMinSize(100.dp)
            .height(35.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = colors,
                        startX = 50f
                    )
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                modifier = Modifier.padding(5.dp),
                text = text,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.onBackground,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }

    }


}