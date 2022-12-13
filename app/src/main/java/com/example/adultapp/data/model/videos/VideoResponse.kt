package com.example.adultapp.data.model.videos


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoResponse(
    @Json(name = "data")
    val data: List<DataVideos>,
    @Json(name = "status")
    val status: Boolean
)