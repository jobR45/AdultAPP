package com.example.adultapp.di

import com.example.adultapp.data.repository.abs.CategoryRepository
import com.example.adultapp.data.repository.abs.VideoRepository
import com.example.adultapp.data.repository.impl.CategoryRepositoryImpl
import com.example.adultapp.data.repository.impl.VideoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)

abstract class RepositoryModule {


    @Binds
    abstract fun providesVideoRepository(videoRepositoryImpl: VideoRepositoryImpl) : VideoRepository

    @Binds
    abstract fun providesCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl) : CategoryRepository


}