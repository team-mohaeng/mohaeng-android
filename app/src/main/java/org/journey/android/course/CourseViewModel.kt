package org.journey.android.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.journey.android.base.DisposableViewModel
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor() : DisposableViewModel(){
    private val _courseRoute = MutableLiveData<List<CourseEntity>>()
    val courseRoute : LiveData<List<CourseEntity>>
        get() = _courseRoute
}