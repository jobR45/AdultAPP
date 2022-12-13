package com.example.adultapp.base

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.adultapp.R
import com.example.adultapp.data.retrofit.EndPointInterceptor
import com.example.adultapp.global.helper.Navigation
import com.example.adultapp.global.helper.SingleLiveEvent
import retrofit2.HttpException

abstract class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {


    //application context for resource access only
    @SuppressLint("StaticFieldLeak")
    protected val applicationContext = application.applicationContext!!


    //for navigation events
    private val _navigation: SingleLiveEvent<Navigation> = SingleLiveEvent()
    val navigation: LiveData<Navigation>
        get() = _navigation

    /**
     * handle throwable for text*/

    protected fun handleThrowableText(throwable: Throwable) :String {
        return if (throwable is EndPointInterceptor.NetworkNotFoundException) {
            applicationContext.getString(R.string.global_error_internet)

        } else if (throwable is HttpException) {
            when (throwable.code()) {
                // we can handle other responses here

                else ->  applicationContext.getString(R.string.global_error)
            }
        } else {
            applicationContext.getString(R.string.global_error)
        }
    }

    /**
     * used for navigation events
     * @param navigationTo  destination
     *
     */

    fun navigate(navigationTo: Navigation) {
        _navigation.value = navigationTo
    }

}