package org.journey.android.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.journey.android.R
import org.journey.android.base.DisposableViewModel
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
                R.drawable.ic_rectangle_1228,
                R.string.course_one,
                R.string.course_subtitle_one,
                R.drawable.grpcrsex1

            ),
            OnboardingCourseEntity(
                R.drawable.ic_rectangle_1229,
                R.string.course_two,
                R.string.course_subtitle_two,
                R.drawable.grpcrsex2
            ),
            OnboardingCourseEntity(
                R.drawable.ic_rectangle_1230,
                R.string.course_three,
                R.string.course_subtitle_three,
                R.drawable.grpcrsex3
            ),
            OnboardingCourseEntity(
                R.drawable.ic_rectangle_1231,
                R.string.course_four,
                R.string.course_subtitle_four,
                R.drawable.grpcrsex4
            ),
            OnboardingCourseEntity(
                R.drawable.ic_rectangle_1232,
                R.string.course_five,
                R.string.course_subtitle_five,
                R.drawable.grpcrsex5
            ),
            OnboardingCourseEntity(
                R.drawable.ic_rectangle_1233,
                R.string.course_six,
                R.string.course_subtitle_six,
                R.drawable.grpcrsex6
            ),
            OnboardingCourseEntity(
                R.drawable.ic_rectanglelove,
                R.string.course_seven,
                R.string.course_subtitle_seven,
                R.drawable.grpcrsex7
            )
        )
        _selectCourse.value = selectCourse
    }


}