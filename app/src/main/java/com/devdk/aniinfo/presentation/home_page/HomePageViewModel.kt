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
import com.devdk.aniinfo.domain.useCases.GetAnimeGenre2
import com.devdk.aniinfo.domain.useCases.GetAnimeGenre3
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getAnime: GetAnime,
    private val genre1: GetAnimeGenre1,
    private val genre2: GetAnimeGenre2,
    private val genre3: GetAnimeGenre3,
) : ViewModel() {

    private val _states = mutableStateOf(States())
     val states : State<States>  = _states

    init {
        getRandomAnime()
        getGenreRow1(states.value.selectedGenreRow1)
        getGenreRow2(states.value.selectedGenreRow2)
        getGenreRow3(states.value.selectedGenreRow3)
    }

    fun onEvent(event: Event){
        when(event){
            is Event.GenreRow1ButtonClick -> {
                _states.value = states.value.copy(
                    selectedGenreRow1 = event.genre
                )
                getGenreRow1(states.value.selectedGenreRow1)
            }
            is Event.GenreRow2ButtonClick -> {
                _states.value = states.value.copy(
                    selectedGenreRow2 = event.genre
                )
                getGenreRow2(states.value.selectedGenreRow2)
            }
            is Event.GenreRow3ButtonClick -> {
                _states.value = states.value.copy(
                    selectedGenreRow3 = event.genre
                )
                getGenreRow3(states.value.selectedGenreRow3)
            }
        }
    }

    private fun getRandomAnime() {
            getAnime.invoke().onEach { resource ->
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
                        _states.value = resource.data?.let {
                            states.value.copy(
                                error = it.message,
                                isLoadingRandom = false
                            )
                        }!!
                    }
                }
            }.launchIn(viewModelScope)
    }


       private fun getGenreRow1(genre: String) {
            genre1.invoke(genre).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _states.value = states.value.copy(
                            genreRow1 = resource.data?.data?.let {
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
                        _states.value = resource.data?.let {
                            states.value.copy(
                                error = it.message,
                                isLoadingRow1 = false
                            )
                        }!!
                    }
                }
            }.launchIn(viewModelScope)
        }

       private fun getGenreRow2(genre : String){
            genre2.invoke(genre).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _states.value = states.value.copy(
                            genreRow2 = resource.data?.data?.let {
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
                        _states.value = resource.data?.let {
                            states.value.copy(
                                error = it.message,
                                isLoadingRow2 = false
                            )
                        }!!
                    }
                }
            }.launchIn(viewModelScope)
        }

        private  fun getGenreRow3(genre: String) {
            genre3.invoke(genre).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _states.value = states.value.copy(
                            genreRow3 = resource.data?.data?.let {
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
                        _states.value = resource.data?.let {
                            states.value.copy(
                                error = it.message,
                                isLoadingRow3 = false
                            )
                        }!!
                    }
                }
            }.launchIn(viewModelScope)
        }
    }