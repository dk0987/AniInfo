package com.devdk.aniinfo.presentation.recommended_page

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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.devdk.aniinfo.R
import com.devdk.aniinfo.common.Backgrounds
import com.devdk.aniinfo.presentation.choose_background.component.ChooseBackgroundCard
import com.devdk.aniinfo.presentation.component.AnimeCard
import com.devdk.aniinfo.presentation.ui.theme.background
import com.devdk.aniinfo.presentation.util.Routes

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun RecommendedScreen(
    navController: NavController ,
    sharedPreferences: SharedPreferences,
    recommendedPageViewModel: RecommendedPageViewModel = hiltViewModel()
) {
    val backgroundColor by remember {
        mutableStateOf(sharedPreferences.getLong("background" , background.value.toLong()).toULong())
    }

    val state = recommendedPageViewModel.states.value

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(backgroundColor))
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
                    contentDescription = stringResource(id = R.string.back),
                    tint = MaterialTheme.colors.onBackground
                )
            }
        }
        LazyVerticalGrid(cells = GridCells.Fixed(2)){
            items(state.randomAnime){ anime ->
                AnimeCard(
                    painter = rememberImagePainter(data = anime.bannerImageURL ?: anime.coverImageURL , builder = {
                        crossfade(true)
                    }),
                    title = anime.title ?: "",
                    Onclick = {
                        navController.navigate(
                            Routes.DetailScreen.screen
                                    +"?id=${anime.id}"
                        )
                    }
                ,isBanner = anime.bannerImageURL != null
                )
            }
        }
    }


}