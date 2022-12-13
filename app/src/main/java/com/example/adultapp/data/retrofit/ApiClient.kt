package com.example.adultapp.data.retrofit

import com.example.adultapp.data.model.category.Categories
import com.example.adultapp.data.model.videos.VideoResponse
import com.example.adultapp.data.model.videos.Videos
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {



    @GET("videos/get-all")
    suspend fun getAllVideos() : Videos

    @GET("videos/get-only-best")
    suspend fun  getBestVideos() :Videos

    @GET("videos/get-recommended")
    suspend fun getRecommended(@Query("video_ids") videoIds :String) :Videos

    @GET("videos/get-by-id")
    suspend fun getVideosByIds(@Query("video_ids") videoIds :String) : VideoResponse

    @GET("videos/search")
    suspend fun getSearchVideos(@Query("query") query: String) : Videos




    @GET("categories/get-all")
    suspend fun getAllCategories() :Categories
}