package com.example.adultapp.di

import com.example.adultapp.global.helper.AppSchedulerProvider
import com.example.adultapp.global.listener.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SchedulerModule {

    @Provides
    @Singleton
    fun providesSchedulerProvider() : SchedulerProvider{
        return  AppSchedulerProvider()
    }
}