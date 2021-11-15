package com.devdk.aniinfo.data.remote.dto.animeDTO

data class DataX(
    val anilist_id: Int,
    val banner_image: String,
    val cover_color: String,
    val cover_image: String,
    val descriptions: DescriptionsX,
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
    val titles: TitlesX,
    val trailer_url: String
)