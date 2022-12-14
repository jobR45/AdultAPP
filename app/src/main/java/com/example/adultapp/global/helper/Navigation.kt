package com.example.adultapp.global.helper

sealed class Navigation {

    object NavigationHome : Navigation()
    data class NavigationDetails ( val videoId : Int) : Navigation()
}