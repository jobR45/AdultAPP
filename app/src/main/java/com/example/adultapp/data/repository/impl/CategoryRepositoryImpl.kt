package com.example.adultapp.data.repository.impl

import com.example.adultapp.data.model.category.DataCategory
import com.example.adultapp.data.repository.abs.CategoryRepository
import com.example.adultapp.data.repository.abs.VideoRepository
import com.example.adultapp.data.retrofit.ApiClient
import javax.inject.Inject

class CategoryRepositoryImpl  @Inject constructor(private val apiClient: ApiClient) : CategoryRepository {

    override suspend fun getAllCategories(): List<DataCategory> {
        return apiClient.getAllCategories().data
    }
}