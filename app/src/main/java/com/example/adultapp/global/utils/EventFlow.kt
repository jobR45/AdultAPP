package com.example.adultapp.global.utils

import kotlinx.coroutines.flow.MutableSharedFlow



/**
 * We can use this instead of SingleLiveEvent*/
fun <T> mutableEventFlow(): MutableSharedFlow<T>{

    return MutableSharedFlow(
        replay = 0,  //the number of values replayed to new subscribers
        extraBufferCapacity = 1 // the number of values buffered in addition to replay.
    )
}