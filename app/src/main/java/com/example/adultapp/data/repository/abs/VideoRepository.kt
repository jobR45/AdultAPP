package com.example.adultapp.data.repository.abs

import androidx.annotation.WorkerThread
import com.example.adultapp.data.model.videos.DataVideos

interface VideoRepository {

    @WorkerThread
    suspend fun getAllVideos() : List<DataVideos>

    @WorkerThread
    suspend fun getBestVideos() : List<DataVideos>

    @WorkerThread
    suspend fun getRecommendedVideos(videoIds :String) : List<DataVideos>


    @WorkerThread
    suspend fun getSearchVideos(query : String) : List<DataVideos>


    @WorkerThread
    suspend fun getVideosByIds(videoIds :String) : List<DataVideos>


}