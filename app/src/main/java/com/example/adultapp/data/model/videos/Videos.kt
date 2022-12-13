package com.example.adultapp.data.model.videos


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Videos(
    @Json(name = "data")
    val data: List<DataVideos>,
    @Json(name = "pagination")
    val pagination: Pagination,
    @Json(name = "status")
    val status: Boolean
)