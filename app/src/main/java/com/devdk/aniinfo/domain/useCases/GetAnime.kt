package com.devdk.aniinfo.domain.useCases

import com.devdk.aniinfo.common.Resource
import com.devdk.aniinfo.data.remote.dto.animeDTO.AnimeDTO
import com.devdk.aniinfo.data.remote.dto.animeListDTO.AnimeListDTO
import com.devdk.aniinfo.data.remote.dto.randomAnimeDTO.RandomAnimeDTO
import com.devdk.aniinfo.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetAnime(
    private val repository: AnimeRepository
) {
     operator fun invoke(): Flow<Resource<RandomAnimeDTO>> = flow {
        try {
            emit(Resource.Loading<RandomAnimeDTO>())
            val dto : RandomAnimeDTO = repository.getRandomAnimeList()
            emit(Resource.Success<RandomAnimeDTO>(dto))
        }
        catch (e : HttpException){
            emit(Resource.Error<RandomAnimeDTO>("Something went wrong"))
        }
        catch (e : IOException){
            emit(Resource.Error<RandomAnimeDTO>("Something went wrong"))
        }
    }

}