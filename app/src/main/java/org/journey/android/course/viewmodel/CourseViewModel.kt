package org.journey.android.course.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.R
import org.journey.android.base.DisposableViewModel
import org.journey.android.course.controller.catalog.CourseCatalogController
import org.journey.android.course.controller.state.CourseStateController
import org.journey.android.course.data.dto.ResponseStartChallengeDTO
import org.journey.android.course.data.entity.CourseCatalogEntity
import org.journey.android.course.data.entity.CourseDateEntity
import org.journey.android.course.data.entity.CourseEntity
import org.journey.android.course.data.repository.CourseCatalogRepository
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val courseCatalogController: CourseCatalogController,
    private val courseStateController: CourseStateController,
    private val courseCatalogRepository: CourseCatalogRepository
) : DisposableViewModel() {
    private val _courseId = MutableLiveData<Int>()
    val courseId : LiveData<Int>
        get() = _courseId

    private val _courseRoute = MutableLiveData<List<CourseEntity>>()
    val courseRoute: LiveData<List<CourseEntity>>
        get() = _courseRoute

    private val _courseCatalogList = MutableLiveData<List<CourseCatalogEntity>>()
    val courseCatalogList: LiveData<List<CourseCatalogEntity>>
        get() = _courseCatalogList

    private val _courseOddDateList = MutableLiveData<List<CourseDateEntity>>()
    val courseDateList: LiveData<List<CourseDateEntity>>
        get() = _courseOddDateList

    private val _startCourse = MutableLiveData<ResponseStartChallengeDTO>()
    val startCourse : LiveData<ResponseStartChallengeDTO>
        get() = _startCourse

    fun changeCourseId(courseId: Int) {
        _courseId.value = courseId
    }

    init {
        fetchCourseRoute()
        fetchOddDateList()
    }


    fun putCourseState(){
        addDisposable(
            courseStateController.putCourseState(
                client = "aos",
                courseId.value ?: -1
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                      _startCourse.postValue(it)
                },{
                    it.printStackTrace()
                })
        )
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


    fun fetchCatalogList() {
        addDisposable(
            courseCatalogRepository.fetchCourseCatalog()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                      _courseCatalogList.postValue(it)
                },{
                    it.printStackTrace()
                })
        )
    }

}