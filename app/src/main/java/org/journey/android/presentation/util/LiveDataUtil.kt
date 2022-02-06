package org.journey.android.presentation.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

fun <T> MediatorLiveData<T>.addSourceList(
    vararg liveDataArgument: MutableLiveData<*>,
    onChanged: () -> T
) {
    liveDataArgument.forEach {
        this.addSource(it) { value = onChanged() }
    }
}

fun <T> MediatorLiveData<T>.addSourceList(
    vararg liveDataArgument: LiveData<*>,
    onChanged: () -> T
) {
    liveDataArgument.forEach {
        this.addSource(it) { value = onChanged() }
    }
}