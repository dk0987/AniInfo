package com.devdk.aniinfo.presentation.util

import android.content.SharedPreferences
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.devdk.aniinfo.presentation.choose_background.ChooseBackground
import com.devdk.aniinfo.presentation.detail_page.DetailPage
import com.devdk.aniinfo.presentation.home_page.HomePage
import com.devdk.aniinfo.presentation.recommended_page.RecommendedScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Navigation(
    sharedPreferences: SharedPreferences ,
    navController: NavController = rememberNavController()
) {
     NavHost(navController = navController as NavHostController, startDestination = Routes.HomeScreen.screen){
         composable(Routes.HomeScreen.screen){
             HomePage(navController = navController , sharedPreferences = sharedPreferences)
         }
         composable(Routes.DetailScreen.screen + "?id={id}" ,
             arguments = listOf(
             navArgument(
                 name = "id"
             ) {
                 type = NavType.IntType
                 defaultValue = -1
             }
             )){
             DetailPage(navController = navController , sharedPreferences = sharedPreferences)
         }
         composable(Routes.ChooseBackground.screen) {
            ChooseBackground(navController = navController, sharedPreferences = sharedPreferences)
         }
         composable(Routes.Recommended.screen) {
             RecommendedScreen(navController = navController, sharedPreferences = sharedPreferences)
         }
     }
}