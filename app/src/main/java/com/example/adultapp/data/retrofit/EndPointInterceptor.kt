package com.example.adultapp.data.retrofit

import android.content.Context
import com.example.adultapp.BuildConfig
import com.example.adultapp.global.utils.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class EndPointInterceptor (private val context : Context): Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("x-api-key", BuildConfig.API_KEY).build()

        if (!context.isNetworkAvailable()) {
            throw NetworkNotFoundException()
        }

        return chain.proceed(request)
    }

    class NetworkNotFoundException : IOException("No network available")
}