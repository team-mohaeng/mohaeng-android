package org.journey.android.presentation.frame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FrameViewModel : ViewModel() {
    private val _pageIndex : MutableLiveData<Int> = MutableLiveData(0)
    val pageIndex : LiveData<Int>
        get() = _pageIndex
    fun changePageIndex(index : Int){
        if(pageIndex.value != index) _pageIndex.value = index
    }
}