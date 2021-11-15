package com.devdk.aniinfo.presentation.util

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.devdk.aniinfo.presentation.detail_page.DetailPage
import com.devdk.aniinfo.presentation.home_page.HomePage
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Navigation(
    navController: NavController = rememberNavController()
) {
     NavHost(navController = navController as NavHostController, startDestination = Routes.HomeScreen.screen){
         composable(Routes.HomeScreen.screen){
             HomePage(navController = navController)
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
             DetailPage(navController = navController)
         }
     }
}