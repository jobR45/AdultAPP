package com.example.adultapp.data.model.videos


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataVideos(
    @Json(name = "categories")
    val categories: List<Int>,
    @Json(name = "default_thumb_url")
    val defaultThumbUrl: String,
    @Json(name = "duration")
    val duration: Int,
    @Json(name = "embed_url")
    val embedUrl: String,
    @Json(name = "has_preview")
    val hasPreview: Boolean,
    @Json(name = "id")
    val id: Int,
    @Json(name = "is_best")
    val isBest: Boolean,
    @Json(name = "other_thumbs_url")
    val otherThumbsUrl: List<String>,
    @Json(name = "preview_url")
    val previewUrl: String,
    @Json(name = "rating")
    val rating: Int,
    @Json(name = "sections")
    val sections: List<String>,
    @Json(name = "source")
    val source: String,
    @Json(name = "source_id")
    val sourceId: String,
    @Json(name = "source_published_day")
    val sourcePublishedDay: Int?,
    @Json(name = "source_published_month")
    val sourcePublishedMonth: Int?,
    @Json(name = "source_published_year")
    val sourcePublishedYear: Int?,
    @Json(name = "title")
    val title: String,
    @Json(name = "title_alphabet")
    val titleAlphabet: String,
    @Json(name = "views_count")
    val viewsCount: Int,
    @Json(name = "votes_count")
    val votesCount: Int,
    @Json(name = "votes_down")
    val votesDown: Int,
    @Json(name = "votes_pct")
    val votesPct: Double,
    @Json(name = "votes_up")
    val votesUp: Int
)