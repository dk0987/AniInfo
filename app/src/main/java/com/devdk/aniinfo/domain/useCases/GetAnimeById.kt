package com.devdk.aniinfo.domain.useCases

import com.devdk.aniinfo.common.Resource
import com.devdk.aniinfo.data.remote.dto.animeDTO.AnimeDTO
import com.devdk.aniinfo.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetAnimeById (
    private val repository: AnimeRepository
 ) {
    operator fun invoke(id: Int): Flow<Resource<AnimeDTO>> = flow  {
            try {
                emit(Resource.Loading<AnimeDTO>())
                val dto: AnimeDTO = repository.getAimeFromId(id)
                emit(Resource.Success<AnimeDTO>(dto))
            } catch (e: HttpException) {
                emit(Resource.Error<AnimeDTO>("Something went wrong"))
            } catch (e: IOException) {
                emit(Resource.Error<AnimeDTO>("Something went wrong"))
            }
      }
    }