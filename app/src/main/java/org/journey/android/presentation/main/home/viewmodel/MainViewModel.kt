package org.journey.android.presentation.main.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.presentation.base.DisposableViewModel
import org.journey.android.presentation.main.home.controller.HomeController
import org.journey.android.presentation.main.home.dto.ResponseHomeDTO
import org.journey.android.presentation.preference.UserPreferenceManager
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

    private val _happinessIndex = MutableLiveData<Int>()
    val happinessIndex : LiveData<Int>
        get() = _happinessIndex

    fun initMohaengMain(){
        addDisposable(
            homeController.getHome(
                client = "aos"
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response->
                    _happinessIndex.postValue(response.challengeProgressDTO.fullHappy*2)
                    _getLottie.postValue(response.challengeProgressDTO)
                    _userNickName.postValue(response.challengeProgressDTO.nickname)
                    _getHomeResource.postValue(true)

                    userPreferenceManager.saveUserNickName(response.challengeProgressDTO.nickname)
                },{
                    _getHomeResource.postValue(false)
                    it.printStackTrace()
                })
        )
    }
}