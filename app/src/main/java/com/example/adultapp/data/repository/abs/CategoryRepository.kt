package com.example.adultapp.data.repository.abs

import androidx.annotation.WorkerThread
import com.example.adultapp.data.model.category.Categories
import com.example.adultapp.data.model.category.DataCategory
import com.example.adultapp.data.model.videos.DataVideos

interface CategoryRepository {


    @WorkerThread
    suspend fun getAllCategories() : List<DataCategory>

}