package com.devdk.aniinfo.data.remote.dto.randomAnimeDTO

import com.devdk.aniinfo.data.remote.dto.animeDTO.AnimeDTO
import com.devdk.aniinfo.domain.modal.AnimeModal

data class RandomAnimeDTO(
    val `data`: List<Data>,
    val message: String,
    val status_code: Int,
    val version: String
)
