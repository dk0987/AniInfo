package com.devdk.aniinfo.presentation.detail_page

data class States(
    val imageURL : String = "",
    val title : String = "",
    val trailerURL : String? = null ,
    val description : String? = null,
    val seasonYear : Int = 0 ,
    val score : Int  = 0,
    val genre : List<String> = emptyList(),
    val isLoading : Boolean = false ,
    val error : String = "",
    val bannerImageUrl : String? = null
)
