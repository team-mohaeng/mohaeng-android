package org.journey.android.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.base.DisposableViewModel
import org.journey.android.main.controller.HomeController
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val homeController: HomeController
) : DisposableViewModel(){
    private val _getHomeResource = MutableLiveData<Boolean>()
    val getHomeResource : LiveData<Boolean>
        get() = _getHomeResource

    fun initMohaengMain(){
        addDisposable(
            homeController.getHome(
                client = "aos"
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ success->
                    _getHomeResource.postValue(true)
                },{
                    _getHomeResource.postValue(false)
                    it.printStackTrace()
                })
        )
    }
}