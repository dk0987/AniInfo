package com.devdk.aniinfo.data.remote.dto.animeListDTO

data class Data(
    val count: Int,
    val current_page: Int,
    val documents: List<Document>,
    val last_page: Int
)
