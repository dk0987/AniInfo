package com.devdk.aniinfo.presentation.recommended_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devdk.aniinfo.common.Resource
import com.devdk.aniinfo.data.remote.dto.randomAnimeDTO.toAnimeModal
import com.devdk.aniinfo.domain.useCases.GetAnime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecommendedPageViewModel @Inject constructor(
    private val getAnime: GetAnime
) : ViewModel(){


    private val _states = mutableStateOf(States())
    val states : State<States> = _states

    init {
        getRandomAnime(100)
    }

    private fun getRandomAnime(page : Int) {
        getAnime(page).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _states.value = states.value.copy(
                        randomAnime = resource.data?.data?.let {
                            it.map { Data ->
                                Data.toAnimeModal()
                            }
                        } ?: emptyList(),
                        isLoadingRandom = false
                    )
                }
                is Resource.Loading -> {
                    _states.value = states.value.copy(
                        isLoadingRandom = true
                    )
                }
                is Resource.Error -> {
                    _states.value = states.value.copy(
                        error = resource.message.toString(),
                        isLoadingRandom = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}