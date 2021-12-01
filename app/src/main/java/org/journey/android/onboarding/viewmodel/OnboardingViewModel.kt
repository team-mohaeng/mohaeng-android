package org.journey.android.onboarding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.journey.android.R
import org.journey.android.base.DisposableViewModel
import org.journey.android.onboarding.data.OnboardingCourseEntity
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor():DisposableViewModel() {
    private val _selectCourse = MutableLiveData<List<OnboardingCourseEntity>>()
    val selectCourse : LiveData<List<OnboardingCourseEntity>>
        get() = _selectCourse

    init{
        fetchCourseList()
    }

    private fun fetchCourseList(){
        val selectCourse = listOf(
            OnboardingCourseEntity(
                R.drawable.crs1
            ),
            OnboardingCourseEntity(
                R.drawable.crs2
            ),
            OnboardingCourseEntity(
                R.drawable.crs3
            ),
            OnboardingCourseEntity(
                R.drawable.crs4
            ),
            OnboardingCourseEntity(
                R.drawable.crs5
            ),
            OnboardingCourseEntity(
                R.drawable.crs6
            ),
            OnboardingCourseEntity(
                R.drawable.crs7
            )
        )
        _selectCourse.value = selectCourse
    }

}