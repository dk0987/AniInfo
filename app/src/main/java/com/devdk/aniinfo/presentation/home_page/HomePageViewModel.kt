package com.devdk.aniinfo.presentation.home_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devdk.aniinfo.common.Resource
import com.devdk.aniinfo.data.remote.dto.animeListDTO.toAnimeModal
import com.devdk.aniinfo.data.remote.dto.randomAnimeDTO.toAnimeModal
import com.devdk.aniinfo.domain.useCases.GetAnime
import com.devdk.aniinfo.domain.useCases.GetAnimeGenre1
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getAnime: GetAnime,
    private val genre1: GetAnimeGenre1,
) : ViewModel() {

    private val _states = mutableStateOf(States())
     val states : State<States>  = _states

    init {
        getRandomAnime()
        getAnimeForGenre1(states.value.selectedGenreRow1)
        getAnimeForGenre2(states.value.selectedGenreRow2)
        getAnimeForGenre3(states.value.selectedGenreRow3)
    }

    fun onEvent(event: Event){
        when(event){
            is Event.GenreRow1ButtonClick -> {
                _states.value = states.value.copy(
                    selectedGenreRow1 = event.genre
                )
                getAnimeForGenre1(states.value.selectedGenreRow1)
            }
            is Event.GenreRow2ButtonClick -> {
                _states.value = states.value.copy(
                    selectedGenreRow2 = event.genre
                )
                getAnimeForGenre2(states.value.selectedGenreRow2)
            }
            is Event.GenreRow3ButtonClick -> {
                _states.value = states.value.copy(
                    selectedGenreRow3 = event.genre
                )
                getAnimeForGenre3(states.value.selectedGenreRow3)
            }
        }
    }

    private fun getRandomAnime() {
            getAnime.invoke(10).onEach { resource ->
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


       private fun getAnimeForGenre1(genre: String) {
            genre1.invoke(genre).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _states.value = states.value.copy(
                            genreRow1 = resource.data?.data?.documents?.let {
                                it.map { Document ->
                                    Document.toAnimeModal()
                                }
                            } ?: emptyList(),
                            isLoadingRow1 = false
                        )
                    }
                    is Resource.Loading -> {
                        _states.value = states.value.copy(
                            isLoadingRow1 = true
                        )
                    }
                    is Resource.Error -> {
                        _states.value = states.value.copy(
                            error = resource.message.toString(),
                            isLoadingRow1 = false
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }

    private fun getAnimeForGenre2(genre: String) {
        genre1.invoke(genre).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _states.value = states.value.copy(
                        genreRow2 = resource.data?.data?.documents?.let {
                            it.map { Document ->
                                Document.toAnimeModal()
                            }
                        } ?: emptyList(),
                        isLoadingRow2 = false
                    )
                }
                is Resource.Loading -> {
                    _states.value = states.value.copy(
                        isLoadingRow2 = true
                    )
                }
                is Resource.Error -> {
                    _states.value = states.value.copy(
                        error = resource.message.toString(),
                        isLoadingRow2 = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAnimeForGenre3(genre: String) {
        genre1.invoke(genre).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _states.value = states.value.copy(
                        genreRow3 = resource.data?.data?.documents?.let {
                            it.map { Document ->
                                Document.toAnimeModal()
                            }
                        } ?: emptyList(),
                        isLoadingRow3 = false
                    )
                }
                is Resource.Loading -> {
                    _states.value = states.value.copy(
                        isLoadingRow3 = true
                    )
                }
                is Resource.Error -> {
                    _states.value = states.value.copy(
                        error = resource.message.toString(),
                        isLoadingRow3 = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
    }