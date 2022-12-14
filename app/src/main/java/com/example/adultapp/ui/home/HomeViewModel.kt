package com.example.adultapp.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.example.adultapp.base.BaseAndroidViewModel
import com.example.adultapp.data.model.category.DataCategory
import com.example.adultapp.data.model.videos.DataVideos
import com.example.adultapp.data.repository.abs.CategoryRepository
import com.example.adultapp.data.repository.abs.VideoRepository
import com.example.adultapp.global.helper.Navigation
import com.example.adultapp.global.helper.Resources
import com.example.adultapp.global.listener.SchedulerProvider
import com.example.adultapp.global.utils.DebugLog
import com.example.adultapp.global.utils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val videoRepository: VideoRepository,
    private val categoryRepository: CategoryRepository,
    private val schedulerProvider: SchedulerProvider
) :
    BaseAndroidViewModel(application) {

    val allVideoResult = MutableLiveData<Resources<List<DataVideos>>>(Resources.Idle())
    val listCategory = MutableLiveData<List<DataCategory>>()

    init {
        getAllVideos()
    }

     private fun getAllVideos(){

        viewModelScope.launch(schedulerProvider.dispatchersUI()) {

            allVideoResult.value = Resources.Loading()
            try {

                val list = withContext(schedulerProvider.dispatchersIO()){
                    videoRepository.getBestVideos()
                }
                allVideoResult.value = Resources.Success(list)




            }catch (e:Exception){

                val errorMessage = handleThrowableText(e)
                allVideoResult.value = Resources.Error(errorMessage)

            }


        }
    }

    fun navigateDetails(videoId :Int){
        if (allVideoResult.value is Resources.Success) {

            navigate(Navigation.NavigationDetails(videoId))

        }
    }
}