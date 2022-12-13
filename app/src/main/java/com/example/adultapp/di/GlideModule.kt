package com.example.adultapp.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object GlideModule {



    @Provides
    @Singleton
    fun providesGlideFragment(context : Context) : RequestManager {

        return Glide.with(context)

    }
}