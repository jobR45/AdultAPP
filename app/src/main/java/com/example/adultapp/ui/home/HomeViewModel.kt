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
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val videoRepository: VideoRepository,
    private val categoryRepository: CategoryRepository,
    private val schedulerProvider: SchedulerProvider
) :
    BaseAndroidViewModel(application) {

    val videoResult = MutableLiveData<Resources<List<List<DataVideos>>>>(Resources.Idle())
    val listCategory = MutableLiveData<List<DataCategory>>()

    private val listResult = mutableListOf<Deferred<List<DataVideos>>>()

    init {
        getAllVideos()
    }

    private fun getAllVideos(){

        viewModelScope.launch(schedulerProvider.dispatchersUI()) {

            videoResult.value = Resources.Loading()
            try {

                addAsync(videoRepository.getAllVideos())
                addAsync(videoRepository.getBestVideos())
                addAsync(videoRepository.getRecommendedVideos("6038221"))  //test value for video_Ids

                if (listResult.isNotEmpty()){
                    videoResult.value = Resources.Success(listResult.awaitAll())
                }
                else{
                    videoResult.value = Resources.Error("Empty list")
                }





            }catch (e:Exception){

                val errorMessage = handleThrowableText(e)
                videoResult.value = Resources.Error(e.message!!)

            }


        }
    }

    private suspend fun addAsync( repo : List<DataVideos>  ){
        withContext(schedulerProvider.dispatchersIO()){
            val list = async {
                repo
            }
            listResult.add(list)


        }
    }

    fun navigateDetails(videoId :Int){
        if (videoResult.value is Resources.Success) {

            navigate(Navigation.NavigationDetails(videoId))

        }
    }
}