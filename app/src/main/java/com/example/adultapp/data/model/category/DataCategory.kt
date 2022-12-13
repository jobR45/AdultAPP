package com.example.adultapp.data.model.category


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataCategory(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "videos_count")
    val videosCount: Int,
    @Json(name = "videos_count_best")
    val videosCountBest: Int
)