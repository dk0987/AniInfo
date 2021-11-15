package com.devdk.aniinfo.presentation.detail_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devdk.aniinfo.common.Resource
import com.devdk.aniinfo.domain.useCases.GetAnimeById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailPageViewModel @Inject constructor(
    private val getAnimeById: GetAnimeById,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _states = mutableStateOf(States())
    val states : State<States> = _states

    init {
        savedStateHandle.get<Int>("id")?.let {
            if (it != -1){
                getAnimeById(it)
            }
        }
    }

    private fun getAnimeById(id : Int) {
        getAnimeById.invoke(id).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.data?.let {
                        _states.value = states.value.copy(
                            imageURL = it.cover_image,
                            trailerURL = it.trailer_url,
                            title = it.titles.en,
                            description = it.descriptions.en,
                            seasonYear = it.season_year,
                            score = it.score,
                            genre = it.genres,
                            isLoading = false
                        )
                    }
                }
                is Resource.Loading -> {
                    _states.value = states.value.copy(
                        isLoading= true
                    )
                }
                is Resource.Error -> {
                    _states.value = states.value.copy(
                        error = resource.message.toString(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}