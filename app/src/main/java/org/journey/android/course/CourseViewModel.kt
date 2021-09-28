package org.journey.android.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.journey.android.R
import org.journey.android.base.DisposableViewModel
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor() : DisposableViewModel(){
    private val _courseRoute = MutableLiveData<List<CourseEntity>>()
    val courseRoute : LiveData<List<CourseEntity>>
        get() = _courseRoute
    private val _courseCatalogList = MutableLiveData<List<CourseCatalogEntity>>()
    val courseCatalogList : LiveData<List<CourseCatalogEntity>>
        get() = _courseCatalogList

    init {
        fetchCourseRoute()
        fetchCatalogList()
    }

    private fun fetchCourseRoute(){
        val courseRoute = listOf(
            CourseEntity(
                R.drawable.ic_default2
        ),
            CourseEntity(
                R.drawable.ic_default3
            ),
            CourseEntity(
                R.drawable.ic_default2
            ),
            CourseEntity(
                R.drawable.ic_default3
            )
        )
        _courseRoute.value = courseRoute
    }

    private fun fetchCatalogList(){
        val courseCatalogList = listOf(
            CourseCatalogEntity(
                "오늘의 일일 DJ",
                "꺅 일탈행 7일 코스",
                R.string.course_catalog_intro,
                R.drawable.ic_img
            ),
            CourseCatalogEntity(
                "오늘의 일일 DJ",
                "꺅 일탈행 7일 코스",
                R.string.course_catalog_intro,
                R.drawable.ic_img
            ),
            CourseCatalogEntity(
                "오늘의 일일 DJ",
                "꺅 일탈행 7일 코스",
                R.string.course_catalog_intro,
                R.drawable.ic_img
            ),
            CourseCatalogEntity(
                "오늘의 일일 DJ",
                "꺅 일탈행 7일 코스",
                R.string.course_catalog_intro,
                R.drawable.ic_img
            )
        )
        _courseCatalogList.value = courseCatalogList
    }
}