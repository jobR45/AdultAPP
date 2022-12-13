package com.example.adultapp.data.model.category


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Categories(
    @Json(name = "data")
    val data: List<DataCategory>,
    @Json(name = "status")
    val status: Boolean
)