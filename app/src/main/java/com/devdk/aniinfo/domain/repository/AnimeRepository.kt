package com.devdk.aniinfo.domain.repository

import com.devdk.aniinfo.common.QueryParams
import com.devdk.aniinfo.data.remote.dto.animeDTO.AnimeDTO
import com.devdk.aniinfo.data.remote.dto.animeListDTO.AnimeListDTO
import com.devdk.aniinfo.data.remote.dto.randomAnimeDTO.RandomAnimeDTO

interface AnimeRepository {

    suspend fun getRandomAnimeList(page : Int = QueryParams.ROW_PAGE_SIZE) : RandomAnimeDTO

    suspend fun getAnimeForGenre(genre : String) : AnimeListDTO

    suspend fun getAimeFromId(id : Int) : AnimeDTO

}