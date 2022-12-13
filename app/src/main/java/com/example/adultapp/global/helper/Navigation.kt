package com.example.adultapp.global.helper

sealed class Navigation {

    object NavigationHome : Navigation()
    object NavigationDetails : Navigation()
}