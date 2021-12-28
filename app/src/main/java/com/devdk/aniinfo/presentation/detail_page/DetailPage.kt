package com.devdk.aniinfo.presentation.detail_page

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.devdk.aniinfo.R
import com.devdk.aniinfo.common.QueryParams
import com.devdk.aniinfo.common.Size
import com.devdk.aniinfo.presentation.detail_page.components.GenreTag
import com.devdk.aniinfo.presentation.component.AnimeCard
import com.devdk.aniinfo.presentation.home_page.HomePageViewModel
import com.devdk.aniinfo.presentation.ui.theme.background
import com.devdk.aniinfo.presentation.util.Routes
import com.google.accompanist.flowlayout.FlowRow
import com.valentinilk.shimmer.shimmer

@ExperimentalMaterialApi
@Composable
fun DetailPage(
    navController: NavController,
    detailViewModel: DetailPageViewModel = hiltViewModel(),
    homePageViewModel: HomePageViewModel = hiltViewModel(),
    sharedPreferences : SharedPreferences
) {

    val backgroundColor by remember {
        mutableStateOf(sharedPreferences.getLong("background" , background.value.toLong()).toULong())
    }

   val state = detailViewModel.states.value
    val context = LocalContext.current
    BoxWithConstraints(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(backgroundColor)),
    ){

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
        ){
            Image(
                painter = rememberImagePainter(data = state.imageURL ),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth,
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent, Color(backgroundColor)
                        ),
                        startY = 40f
                    )
                ),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = BottomCenter
        ){
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ){
                    item {
                        if (state.isLoading || state.error.isNotEmpty()){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .shimmer(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "",
                                    modifier = Modifier
                                        .background(Color.Black)
                                        .fillMaxWidth(0.8f)
                                )

                                Image(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape),
                                    painter = painterResource(id = R.drawable.download),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Column(Modifier.shimmer().fillMaxWidth() , horizontalAlignment = Alignment.Start) {
                                Spacer(modifier = Modifier.height(5.dp).fillMaxWidth())
                                Text(
                                    text = "",
                                    modifier = Modifier
                                        .background(Color.Black)
                                        .fillMaxWidth()
                                )
                                Text(
                                    text = "",
                                    modifier = Modifier
                                        .background(Color.Black)
                                        .fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(Size.large))
                                Text(
                                    text = "",
                                    lineHeight = 50.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Black)
                                )
                            }
                            Spacer(modifier = Modifier.height(Size.large))
                        }
                        else{
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = state.title,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 30.sp,
                                    color = MaterialTheme.colors.onBackground,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.fillMaxWidth(0.7f)
                                )
                                if (state.trailerURL != null){
                                    Image(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .clickable {
                                                val intent = Intent(
                                                    Intent.ACTION_VIEW,
                                                    Uri.parse(state.trailerURL)
                                                )
                                                context.startActivity(intent)
                                            }
                                            .clip(CircleShape),
                                        painter = painterResource(id = R.drawable.ic_icons8_youtube),
                                        contentDescription = stringResource(
                                            id = R.string.YOUTUBE_ICON
                                        ),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                            Column(Modifier.fillMaxWidth() , horizontalAlignment = Alignment.Start) {
                                Text(
                                    text = "Score : " + state.score.toString(),
                                    color = MaterialTheme.colors.onBackground,
                                    textAlign = TextAlign.Start
                                )
                                Text(
                                    text = "Season Year : " + state.seasonYear.toString(),
                                    color = MaterialTheme.colors.onBackground,
                                    textAlign = TextAlign.Start
                                )
                            }
                            Spacer(modifier = Modifier.height(Size.large))
                            Text(
                                text = state.description ?: "",
                                color = MaterialTheme.colors.onBackground,
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.Justify
                            )
                            Spacer(modifier = Modifier.height(Size.large))
                            FlowRow(
                                mainAxisSpacing = 10.dp,
                                crossAxisSpacing = 10.dp
                            ) {
                                state.genre.forEach {
                                    GenreTag(genre = it)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(Size.large))
                        LazyRow{
                            if (homePageViewModel.states.value.isLoadingRandom || state.error.isNotEmpty()){
                                items(QueryParams.ROW_PAGE_SIZE){
                                    Box(modifier = Modifier.shimmer()){
                                        AnimeCard(
                                            painter = rememberImagePainter(R.drawable.download),
                                        )
                                    }
                                }
                            }
                            else{
                                items(homePageViewModel.states.value.randomAnime){genre ->
                                    AnimeCard(
                                        painter = rememberImagePainter(data = genre.coverImageURL, builder = {
                                            crossfade(true)
                                        }),
                                        title = genre.title ?: "",
                                        Onclick = {
                                            navController.popBackStack()
                                            navController.navigate(
                                                Routes.DetailScreen.screen
                                                        +"?id=${genre.id}"
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
    }
    IconButton(onClick = {
        navController.navigateUp()
    }) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(id = R.string.BACK_ARROW),
            modifier = Modifier
                .padding(start = 10.dp , top = 10.dp),
            tint = Color.White
        )
    }
}



























