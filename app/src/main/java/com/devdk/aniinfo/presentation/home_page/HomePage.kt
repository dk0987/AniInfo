package com.devdk.aniinfo.presentation.home_page

import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.devdk.aniinfo.R
import com.devdk.aniinfo.presentation.component.Banner
import com.devdk.aniinfo.common.Size
import com.devdk.aniinfo.presentation.component.AnimeCard
import com.devdk.aniinfo.presentation.home_page.components.Buttons
import com.devdk.aniinfo.presentation.util.Routes
import com.devdk.aniinfo.common.Genres
import com.devdk.aniinfo.common.QueryParams
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.valentinilk.shimmer.shimmer

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomePageViewModel = hiltViewModel(),
    sharedPreferences: SharedPreferences
) {

    val state = viewModel.states.value
    val pagerState = rememberPagerState()
    val color = sharedPreferences.getLong("background" , MaterialTheme.colors.background.value.toLong()).toULong()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(color))
    ){
        IconButton(onClick = {
            navController.navigate(Routes.ChooseBackground.screen)
        },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(imageVector = Icons.Default.Settings, contentDescription = stringResource(
                id = R.string.setting
            ),
                tint = MaterialTheme.colors.onBackground
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.onBackground ,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 40.sp,
                        )
                    ){
                        append("A")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.onBackground ,
                            fontWeight = FontWeight.Normal,
                            fontSize = 35.sp,
                        )
                    ){
                        append("ni")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.onBackground ,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 40.sp,
                        )
                    ){
                        append("I")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.onBackground ,
                            fontWeight = FontWeight.Normal,
                            fontSize = 35.sp,
                        )
                    ){
                        append("nfo")
                    }
                },
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                ,
                letterSpacing = 5.sp,
            )

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                item {
                    if (state.isLoadingRandom || state.error.isNotEmpty()){
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.35f)
                            .padding(10.dp)
                            .shimmer()
                        ){
                            Banner(
                                painter = painterResource(id = R.drawable.download),
                                cornerShape = 12.dp,
                                isBanner = true ,
                            )
                        }
                    }
                    else {
                        HorizontalPager(
                            count = state.randomAnime.size ,
                            state = pagerState ,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.35f)
                                .padding(10.dp)
                        ) {
                            Banner(
                                painter = rememberImagePainter(
                                    data = state.randomAnime[currentPage].bannerImageURL
                                        ?: state.randomAnime[currentPage].coverImageURL,
                                    builder = {
                                        crossfade(true)
                                    },
                                ),
                                cornerShape = 12.dp,
                                Onclick = {
                                    navController.navigate(
                                        Routes.DetailScreen.screen
                                                +"?id=${state.randomAnime[currentPage].id}"
                                    )
                                },
                                isBanner = state.randomAnime[currentPage].bannerImageURL != null ,
                            )
                        }
                        Spacer(modifier = Modifier.height(Size.small))
                        HorizontalPagerIndicator(
                            pagerState = pagerState,
                            activeColor = MaterialTheme.colors.onBackground,
                            inactiveColor = Color.Gray,
                        )
                    }
                    Spacer(modifier = Modifier.height(Size.small))
                    LazyRow(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        items(Genres.genresRow1){ genre ->
                            Buttons(text = genre , OnClick = {
                                viewModel.onEvent(
                                    Event.GenreRow1ButtonClick(
                                        genre
                                    )
                                )
                            })
                        }
                        item {
                            Text(
                                text = stringResource(id = R.string.see_more),
                                color = MaterialTheme.colors.onBackground  ,
                                fontWeight = FontWeight.SemiBold ,
                                fontSize = 18.sp ,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.clickable {
                                    navController.navigate(Routes.Recommended.screen)
                                }
                            )
                        }
                    }
                    LazyRow{
                        if (state.isLoadingRow1 || state.error.isNotEmpty()){
                            items(QueryParams.ROW_PAGE_SIZE){
                                Box(modifier = Modifier.shimmer()){
                                    AnimeCard(
                                        painter = rememberImagePainter(R.drawable.download),
                                        isBanner = false
                                    )
                                }
                            }
                        }
                        else{
                            items(state.genreRow1){genre ->
                                AnimeCard(
                                    painter = rememberImagePainter(data = genre.coverImageURL, builder = {
                                        crossfade(true)
                                    }),
                                    title = genre.title ?: "",
                                    Onclick = {
                                        navController.navigate(
                                            Routes.DetailScreen.screen
                                                    +"?id=${genre.id}"
                                        )
                                    },
                                    isBanner = genre.bannerImageURL != null
                                )
                            }
                        }
                    }
                    LazyRow(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        items(Genres.genresRow2){ genre ->
                            Buttons(text = genre , OnClick = {
                                viewModel.onEvent(
                                    Event.GenreRow2ButtonClick(
                                        genre
                                    )
                                )
                            })
                        }
                        item {
                            Text(
                                text = stringResource(id = R.string.see_more),
                                color = MaterialTheme.colors.onBackground ,
                                fontWeight = FontWeight.SemiBold ,
                                fontSize = 18.sp ,
                                textDecoration = TextDecoration.Underline ,
                                modifier = Modifier.clickable {
                                    navController.navigate(Routes.Recommended.screen)
                                }
                            )
                        }
                    }
                    LazyRow(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        if (state.isLoadingRow2 || state.error.isNotEmpty()){
                            items(QueryParams.ROW_PAGE_SIZE){
                                Box(modifier = Modifier.shimmer()){
                                    AnimeCard(
                                        painter = rememberImagePainter(R.drawable.download),
                                        isBanner = false
                                    )
                                }
                            }
                        }
                        else{
                            items(state.genreRow2){genre ->
                                AnimeCard(
                                    painter = rememberImagePainter(data = genre.coverImageURL ,builder = {
                                        crossfade(true)
                                    }),
                                    title = genre.title ?: "",
                                    Onclick = {
                                        navController.navigate(
                                        Routes.DetailScreen.screen
                                                +"?id=${genre.id}"
                                    )},
                                    isBanner = genre.bannerImageURL != null
                                )
                            }
                        }
                    }
                    LazyRow(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        items(Genres.genresRow3){ genre ->
                            Buttons(text = genre , OnClick = {
                                viewModel.onEvent(
                                    Event.GenreRow3ButtonClick(
                                        genre
                                    )
                                )
                            })
                        }
                        item {
                            Text(
                                text = stringResource(id = R.string.see_more),
                                color = MaterialTheme.colors.onBackground  ,
                                fontWeight = FontWeight.SemiBold ,
                                fontSize = 18.sp ,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.clickable {
                                    navController.navigate(Routes.Recommended.screen)
                                }
                            )
                        }
                    }
                    LazyRow{
                        if (state.isLoadingRow3 || state.error.isNotEmpty()){
                            items(QueryParams.ROW_PAGE_SIZE){
                                Box(modifier = Modifier.shimmer()){
                                    AnimeCard(
                                        painter = rememberImagePainter(R.drawable.download),
                                        isBanner = false
                                    )
                                }
                            }
                        }
                        else{
                            items(state.genreRow3){genre ->
                                AnimeCard(
                                    painter = rememberImagePainter(data = genre.coverImageURL , builder = {
                                        crossfade(true)
                                    }),
                                    title = genre.title ?: "",
                                    Onclick = {
                                        navController.navigate(
                                            Routes.DetailScreen.screen
                                                    +"?id=${genre.id}"
                                        )
                                    },
                                    isBanner = genre.bannerImageURL != null
                                )
                            }
                        }
                    }
                }
            }

        }

    }

}