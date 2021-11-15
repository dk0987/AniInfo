package com.devdk.aniinfo.data.repository

import com.devdk.aniinfo.data.remote.AnimeAPI
import com.devdk.aniinfo.data.remote.dto.animeDTO.AnimeDTO
import com.devdk.aniinfo.data.remote.dto.animeListDTO.AnimeListDTO
import com.devdk.aniinfo.data.remote.dto.randomAnimeDTO.RandomAnimeDTO
import com.devdk.aniinfo.domain.repository.AnimeRepository

class AnimeRepositoryImpl(
    private val api :AnimeAPI
) : AnimeRepository {
    override suspend fun getRandomAnimeList(): RandomAnimeDTO{
       return api.getRandomAnimeList()
    }

    override suspend fun getAnimeForGenreRow1(genre: String): AnimeListDTO{
        return api.getAnimeForGenreRow1(genre)
    }

    override suspend fun getAnimeForGenreRow2(genre: String): AnimeListDTO {
        return api.getAnimeForGenreRow2(genre)
    }

    override suspend fun getAnimeForGenreRow3(genre: String): AnimeListDTO{
        return api.getAnimeForGenreRow3(genre)
    }

    override suspend fun getAimeFromId(id: Int): AnimeDTO {
       return api.getAimeFromId(id)
    }
}