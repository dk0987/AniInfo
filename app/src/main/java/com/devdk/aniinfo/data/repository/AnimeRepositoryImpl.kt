package com.devdk.aniinfo.data.repository

import com.devdk.aniinfo.data.remote.AnimeAPI
import com.devdk.aniinfo.data.remote.dto.animeDTO.AnimeDTO
import com.devdk.aniinfo.data.remote.dto.animeListDTO.AnimeListDTO
import com.devdk.aniinfo.data.remote.dto.randomAnimeDTO.RandomAnimeDTO
import com.devdk.aniinfo.domain.repository.AnimeRepository

class AnimeRepositoryImpl(
    private val api :AnimeAPI
) : AnimeRepository {
    override suspend fun getRandomAnimeList(page : Int): RandomAnimeDTO{
       return api.getRandomAnimeList(page)
    }

    override suspend fun getAnimeForGenre(genre: String): AnimeListDTO{
        return api.getAnimeForGenreRow1(genre)
    }

    override suspend fun getAimeFromId(id: Int): AnimeDTO {
       return api.getAnimeFromId(id)
    }
}