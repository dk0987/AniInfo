package com.devdk.aniinfo.domain.repository

import com.devdk.aniinfo.data.remote.dto.animeDTO.AnimeDTO
import com.devdk.aniinfo.data.remote.dto.animeListDTO.AnimeListDTO
import com.devdk.aniinfo.data.remote.dto.randomAnimeDTO.RandomAnimeDTO

interface AnimeRepository {

    suspend fun getRandomAnimeList() : RandomAnimeDTO

    suspend fun getAnimeForGenreRow1(genre : String) : RandomAnimeDTO

    suspend fun getAnimeForGenreRow2(genre : String) : RandomAnimeDTO

    suspend fun getAnimeForGenreRow3(genre : String) : RandomAnimeDTO

    suspend fun getAimeFromId(id : Int) : AnimeDTO

}