package com.devdk.aniinfo.presentation.choose_background

import android.content.SharedPreferences
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devdk.aniinfo.R
import com.devdk.aniinfo.common.Backgrounds
import com.devdk.aniinfo.presentation.choose_background.component.ChooseBackgroundCard

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun ChooseBackground(
    navController: NavController,
    sharedPreferences: SharedPreferences
) {

    var backgroundColor by remember {
        mutableStateOf(sharedPreferences.getLong("background" , 0).toULong())
    }

   Column(modifier = Modifier
       .fillMaxSize()
       .background(Color(backgroundColor))
       .padding(10.dp)
       ){
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(start = 5.dp),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.Start
       ) {
           IconButton(onClick = { navController.navigateUp() } , modifier = Modifier.size(25.dp)) {
               Icon(
                   imageVector = Icons.Default.KeyboardArrowLeft,
                   contentDescription = stringResource(id = R.string.back)
               )
           }

       }
       LazyVerticalGrid(cells = GridCells.Fixed(2)){
           items(Backgrounds.backgroundColors){ color ->
               ChooseBackgroundCard(color = color) {
                   sharedPreferences.edit()
                       .putLong("background" , color.value.toLong())
                       .apply()
                   backgroundColor = color.value
               }
           }
       }
   }


}