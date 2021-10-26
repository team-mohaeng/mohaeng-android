package org.journey.android.course.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.journey.android.R
import org.journey.android.base.DisposableViewModel
import org.journey.android.course.data.entity.CourseCatalogEntity
import org.journey.android.course.data.entity.CourseDateEntity
import org.journey.android.course.data.entity.CourseEntity
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor() : DisposableViewModel() {
    private val _courseRoute = MutableLiveData<List<CourseEntity>>()
    val courseRoute: LiveData<List<CourseEntity>>
        get() = _courseRoute

    private val _courseCatalogList = MutableLiveData<List<CourseCatalogEntity>>()
    val courseCatalogList: LiveData<List<CourseCatalogEntity>>
        get() = _courseCatalogList

    private val _courseOddDateList = MutableLiveData<List<CourseDateEntity>>()
    val courseDateList: LiveData<List<CourseDateEntity>>
        get() = _courseOddDateList


    init {
        fetchCourseRoute()
        fetchCatalogList()
        fetchOddDateList()
    }

    private fun fetchCourseRoute() {
        val courseRoute = listOf(
            CourseEntity(
                null
            ),
            CourseEntity(
                R.drawable.ic_course_gray
            ),
            CourseEntity(
                R.drawable.ic_course_gray_3
            ),
            CourseEntity(
                R.drawable.ic_course_gray
            ),
            CourseEntity(
                R.drawable.ic_course_gray_3
            ),
            CourseEntity(
                R.drawable.ic_course_gray
            ),
            CourseEntity(
                R.drawable.ic_course_gray_3
            ),
            CourseEntity(
                null
            )
        )
        _courseRoute.value = courseRoute
    }

    private fun fetchOddDateList() {
        val courseOddDateList = listOf(
            CourseDateEntity(
                R.drawable.stamp,
                "1일차",
                "3개 국어 인삿말 말하기"
            ),
            CourseDateEntity(
                R.drawable.stamp,
                "2일차",
                "3개 국어 인삿말 말하기"
            ),
            CourseDateEntity(
                R.drawable.stamp,
                "3일차",
                "3개 국어 인삿말 말하기"
            ),
            CourseDateEntity(
                R.drawable.stamp,
                "4일차",
                "3개 국어 인삿말 말하기"
            ),
            CourseDateEntity(
                R.drawable.stamp,
                "5일차",
                "3개 국어 인삿말 말하기"
            ),
            CourseDateEntity(
                R.drawable.stamp,
                "6일차",
                "3개 국어 인삿말 말하기"
            ),
            CourseDateEntity(
                R.drawable.stamp,
                "7일차",
                "3개 국어 인삿말 말하기"
            )
        )
        _courseOddDateList.value = courseOddDateList
    }


    private fun fetchCatalogList() {
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