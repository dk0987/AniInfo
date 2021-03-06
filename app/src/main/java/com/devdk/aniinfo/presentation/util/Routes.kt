package com.devdk.aniinfo.presentation.util

sealed class Routes(val screen : String){
    object HomeScreen : Routes("Home")
    object DetailScreen : Routes("Detail")
    object ChooseBackground : Routes("ChooseBackground")
    object Recommended : Routes("Recommended")
}
