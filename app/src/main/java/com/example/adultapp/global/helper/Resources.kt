package com.example.adultapp.global.helper

sealed class Resources<T> {

    class Idle<T> : Resources<T>()
    class Loading<T> : Resources<T>()
    data class Success<T>(
        val data : T,
        val message : String? = null
    ) : Resources<T>()
    data class Error<T>(
        val error : String
    ) :Resources<T>()
}