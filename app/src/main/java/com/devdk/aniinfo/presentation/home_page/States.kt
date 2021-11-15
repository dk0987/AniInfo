package com.devdk.aniinfo.presentation.home_page

import com.devdk.aniinfo.common.Genres
import com.devdk.aniinfo.domain.modal.AnimeModal

data class States(
    val randomAnime : List<AnimeModal> = emptyList(),
    val genreRow1 : List<AnimeModal> = emptyList(),
    val genreRow2 : List<AnimeModal> = emptyList(),
    val genreRow3 : List<AnimeModal> = emptyList(),
    val selectedGenreRow1 : String = Genres.genresRow1[0],
    val selectedGenreRow2 : String = Genres.genresRow2[0],
    val selectedGenreRow3 : String = Genres.genresRow3[0],
    val error : String = "",
    val isLoadingRandom : Boolean = false,
    val isLoadingRow1 : Boolean = false,
    val isLoadingRow2 : Boolean = false,
    val isLoadingRow3 : Boolean = false,
)
