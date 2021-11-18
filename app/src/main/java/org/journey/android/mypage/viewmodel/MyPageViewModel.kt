package org.journey.android.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.base.DisposableViewModel
import org.journey.android.mypage.controller.course.CompleteCourseController
import org.journey.android.mypage.controller.user.MyPageController
import org.journey.android.mypage.data.dto.CompleteCourseDTO
import org.journey.android.mypage.data.entity.CompleteCourseEntity
import org.journey.android.mypage.data.dto.response.ResponseCheckMyPageDTO
import org.journey.android.mypage.data.dto.response.ResponseCompleteCourseDTO
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageController: MyPageController,
    private val completeCourseController: CompleteCourseController
) : DisposableViewModel() {
    private val _myPageResource = MutableLiveData<Boolean>()
    val myPageResource: LiveData<Boolean>
        get() = _myPageResource

    private val _fetchMyPageSource = MutableLiveData<ResponseCheckMyPageDTO>()
    val fetchMyPageSource : LiveData<ResponseCheckMyPageDTO>
        get() = _fetchMyPageSource

    private val _showUserSituation = MutableLiveData<ResponseCheckMyPageDTO.Data>()
    val showUserSituation : LiveData<ResponseCheckMyPageDTO.Data>
        get() = _showUserSituation

    private val _getCompleteCourse = MutableLiveData<List<CompleteCourseEntity>>()
    val getCompleteCourse : LiveData<List<CompleteCourseEntity>>
        get() = _getCompleteCourse

    private val _fetchCompletedCourse = MutableLiveData<ResponseCompleteCourseDTO>()
    val fetchCompleteCourse : LiveData<ResponseCompleteCourseDTO>
        get() = _fetchCompletedCourse

    private val _isCompleteCourse = MutableLiveData<CompleteCourseDTO>()
    val isCompleteCourse : LiveData<CompleteCourseDTO>
        get() = _isCompleteCourse

    fun getMyPageResource() {
        addDisposable(
            myPageController.myPageResource()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    _fetchMyPageSource.postValue(response)
                }, {
                    _myPageResource.postValue(false)
                    it.printStackTrace()
                })
        )
    }

    fun showCompleteCourse(){
       addDisposable(
           completeCourseController.completeCourse()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe({ response ->
                   _fetchCompletedCourse.postValue(response)

               },{
                   it.printStackTrace()
               })
       )
    }
}