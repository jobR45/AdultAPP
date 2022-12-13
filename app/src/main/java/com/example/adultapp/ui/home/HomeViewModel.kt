package com.example.adultapp.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.adultapp.base.BaseAndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(application: Application) : BaseAndroidViewModel(application) {

    //business logic
}