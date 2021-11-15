package org.journey.android.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.base.DisposableViewModel
import org.journey.android.main.controller.HomeController
import org.journey.android.main.dto.ResponseHomeDTO
import org.journey.android.preference.UserPreferenceManager
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val homeController: HomeController,
    private val userPreferenceManager: UserPreferenceManager
) : DisposableViewModel(){
    private val _getHomeResource = MutableLiveData<Boolean>()
    val getHomeResource : LiveData<Boolean>
        get() = _getHomeResource

    private val _getLottie = MutableLiveData<ResponseHomeDTO.ChallengeProgressDTO>()
    val getLottie : LiveData<ResponseHomeDTO.ChallengeProgressDTO>
        get() = _getLottie

    private val _userNickName = MutableLiveData<String>()
    val userNickName : LiveData<String>
        get() = _userNickName


    fun initMohaengMain(){
        addDisposable(
            homeController.getHome(
                client = "aos"
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ reponse->
                    _getLottie.postValue(reponse.challengeProgressDTO)
                    _userNickName.postValue(reponse.challengeProgressDTO.nicknema)
                    _getHomeResource.postValue(true)
                },{
                    _getHomeResource.postValue(false)
                    it.printStackTrace()
                })
        )
    }
}