package com.example.adultapp.ui.splash

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adultapp.base.BaseAndroidViewModel
import com.example.adultapp.global.helper.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(application: Application) :BaseAndroidViewModel(application) {



    init {
        viewModelScope.launch {
            delay(3000L)
            navigate(Navigation.NavigationHome)
        }
    }




}