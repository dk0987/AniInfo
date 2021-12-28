package com.devdk.aniinfo.domain.useCases

import com.devdk.aniinfo.common.Resource
import com.devdk.aniinfo.data.remote.dto.animeListDTO.AnimeListDTO
import com.devdk.aniinfo.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetAnimeGenre1(
    private val repository : AnimeRepository
) {
     operator fun invoke (genre : String) : Flow<Resource<AnimeListDTO>> = flow{
        try {
            emit(Resource.Loading<AnimeListDTO>())
            val dto : AnimeListDTO = repository.getAnimeForGenre(genre)
            emit(Resource.Success<AnimeListDTO>(dto))
        }
        catch (e : HttpException){
            emit(Resource.Error<AnimeListDTO>(e.localizedMessage ?: "Check Your Internet Connection " ))
        }
        catch (e : IOException){
            emit(Resource.Error<AnimeListDTO>("Something went wrong"))
        }
    }
}