package com.devdk.aniinfo.presentation.detail_page.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devdk.aniinfo.presentation.ui.theme.tagBlue

@Composable
fun GenreTag(
    genre : String
) {
    Box(modifier = Modifier
        .border(
        width = 2.dp,
        color = tagBlue,
        shape = RoundedCornerShape(15.dp)),
        contentAlignment = Alignment.Center
    ){
        Text(
            modifier = Modifier.padding(horizontal = 10.dp , vertical = 5.dp),
            text = genre,
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Thin
        )
    }
}