package com.devdk.aniinfo.data.remote

import com.devdk.aniinfo.data.remote.dto.animeDTO.AnimeDTO
import com.devdk.aniinfo.data.remote.dto.animeListDTO.AnimeListDTO
import com.devdk.aniinfo.data.remote.dto.randomAnimeDTO.RandomAnimeDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeAPI {

    @GET("random/anime/{count}")
    suspend fun getRandomAnimeList(
        @Path("count") count : Int = 8
    ) : RandomAnimeDTO

    @GET("random/anime/20")
    suspend fun getAnimeForGenreRow1(
        @Query("genres") genre : String ,
        @Query("per_page")page : Int = 20
    ) : RandomAnimeDTO

    @GET("random/anime/20")
    suspend fun getAnimeForGenreRow2(
        @Query("genres") genre : String ,
        @Query("per_page")page : Int = 20
    ) : RandomAnimeDTO

    @GET("random/anime/20")
    suspend fun getAnimeForGenreRow3(
        @Query("genres") genre : String ,
        @Query("per_page")page : Int = 20
    ) : RandomAnimeDTO

    @GET("anime/{id}")
    suspend fun getAimeFromId(
        @Path("id") id : Int
    ) : AnimeDTO

}