package org.journey.android.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.base.DisposableViewModel
import org.journey.android.mypage.controller.MyPageController
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageController: MyPageController
) :
    DisposableViewModel() {

    private val _myPageResource = MutableLiveData<Boolean>()
    val myPageResource : LiveData<Boolean>
        get() = _myPageResource

    fun getMyPageResource(){
        addDisposable(
            myPageController.myPageResource()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _myPageResource.postValue(true)
                },{
                    _myPageResource.postValue(false)
                    it.printStackTrace()
                })
        )
    }
}