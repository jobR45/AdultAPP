package com.example.adultapp.di

import com.example.adultapp.BuildConfig
import com.example.adultapp.data.retrofit.ApiClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiClientModule {


    @Provides
    @Singleton
    fun providesApiClient(retrofit:Retrofit) :ApiClient{
        return retrofit.create(ApiClient::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient,moshi: Moshi) : Retrofit{

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(  MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi() : Moshi {
        return  Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    }

}