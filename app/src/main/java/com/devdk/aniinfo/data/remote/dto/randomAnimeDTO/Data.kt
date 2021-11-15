package com.devdk.aniinfo.data.remote.dto.randomAnimeDTO

import com.devdk.aniinfo.domain.modal.AnimeModal

data class Data(
    val anilist_id: Int,
    val banner_image: String,
    val cover_color: String,
    val cover_image: String,
    val descriptions: Descriptions,
    val end_date: String,
    val episode_duration: Int,
    val episodes_count: Int,
    val format: Int,
    val genres: List<String>,
    val id: Int,
    val mal_id: Int,
    val prequel: Int,
    val score: Int,
    val season_period: Int,
    val season_year: Int,
    val start_date: String,
    val status: Int,
    val titles: Titles,
    val trailer_url: String
)


fun Data.toAnimeModal() : AnimeModal {
    return AnimeModal(
        title = titles.en,
        description = descriptions.en,
        seasonYear = season_year,
        coverImageURL = cover_image,
        bannerImageURL = banner_image,
        genres = genres,
        id = id
    )
}