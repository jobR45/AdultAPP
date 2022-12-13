package com.example.adultapp.global.helper

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.adultapp.global.utils.DebugLog
import com.example.adultapp.global.utils.TAG
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {

    /*
    *
    extension of the MutableLiveData class that it emits the data only once whenever required.
    * this ensure  that events will trigger only once upon configuration change/screen rotation..*/


    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        if (hasActiveObservers()) {
            DebugLog.w(
                TAG,
                "Multiple observers registered but only one will be notified of changes."
            )
        }

        // Observe the   MutableLiveData
        super.observe(owner, Observer { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }
}