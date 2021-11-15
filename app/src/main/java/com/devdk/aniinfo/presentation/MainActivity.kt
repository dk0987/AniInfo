package com.devdk.aniinfo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.devdk.aniinfo.presentation.home_page.HomePage
import com.devdk.aniinfo.presentation.ui.theme.AniInfoTheme
import com.devdk.aniinfo.presentation.util.Navigation
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AniInfoTheme {
                Navigation()
            }
        }
    }
}

