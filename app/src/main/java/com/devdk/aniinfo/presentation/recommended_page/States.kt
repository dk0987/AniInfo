package com.devdk.aniinfo.presentation.recommended_page

import com.devdk.aniinfo.domain.modal.AnimeModal

data class States(
    val randomAnime : List<AnimeModal> = emptyList(),
    val error : String = "",
    val isLoadingRandom : Boolean = false,
)
