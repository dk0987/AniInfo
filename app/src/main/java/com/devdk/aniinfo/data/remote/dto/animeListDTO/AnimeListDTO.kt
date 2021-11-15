package com.devdk.aniinfo.data.remote.dto.animeListDTO


data class AnimeListDTO(
    val `data`: Data,
    val message: String,
    val status_code: Int,
    val version: String
)

