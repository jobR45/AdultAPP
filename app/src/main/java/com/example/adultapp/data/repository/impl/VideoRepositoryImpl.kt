package com.example.adultapp.data.repository.impl

import com.example.adultapp.data.model.videos.DataVideos
import com.example.adultapp.data.repository.abs.VideoRepository
import com.example.adultapp.data.retrofit.ApiClient
import javax.inject.Inject

class VideoRepositoryImpl  @Inject constructor(private val apiClient: ApiClient) : VideoRepository {



    override suspend fun getAllVideos(): List<DataVideos> {
       return apiClient.getAllVideos().data
    }

    override suspend fun getBestVideos(): List<DataVideos> {
       return apiClient.getBestVideos().data
    }

    override suspend fun getRecommendedVideos(videoIds: String): List<DataVideos> {
        return apiClient.getRecommended(videoIds).data
    }

    override suspend fun getSearchVideos(query: String): List<DataVideos> {
        return apiClient.getSearchVideos(query).data
    }

    override suspend fun getVideosByIds(videoIds: String): List<DataVideos> {
       return apiClient.getVideosByIds(videoIds).data
    }
}