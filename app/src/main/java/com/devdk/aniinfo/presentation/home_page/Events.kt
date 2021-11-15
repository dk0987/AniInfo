package com.devdk.aniinfo.presentation.home_page

sealed class Event{
    data class GenreRow1ButtonClick(val genre : String) : Event()
    data class GenreRow2ButtonClick(val genre : String) : Event()
    data class GenreRow3ButtonClick(val genre : String) : Event()
}
