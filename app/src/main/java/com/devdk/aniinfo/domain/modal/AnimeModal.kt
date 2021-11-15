package com.devdk.aniinfo.domain.modal

data class AnimeModal (
    val title : String? ,
    val description :String? ,
    val seasonYear : Int? ,
    val trailerURL : String? = null ,
    val coverImageURL : String? ,
    val bannerImageURL : String? = null ,
    val genres : List<String>? ,
    val id : Int
)