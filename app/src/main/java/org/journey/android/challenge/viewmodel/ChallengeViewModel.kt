package org.journey.android.challenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.base.DisposableViewModel
import org.journey.android.challenge.controller.ChallengeController
import javax.inject.Inject

@HiltViewModel
class ChallengeViewModel @Inject constructor(
    private val challengeController: ChallengeController
) : DisposableViewModel() {
    val courseId = MutableLiveData<String>("courseId")
    val challengeId = MutableLiveData<String>("challengeId")

    private val _todayChallengeList = MutableLiveData<Boolean>()
    val todayChallengeList : LiveData<Boolean>
        get() = _todayChallengeList

    private val _validateChallenge = MutableLiveData<Boolean>()
    val validateChallenge : LiveData<Boolean>
        get() = _validateChallenge

    fun getTodayChallenge(){
        addDisposable(
            challengeController.todayChallenge(
                client = "aos"
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                     _todayChallengeList.postValue(true)
                },{
                    _todayChallengeList.postValue(false)
                    it.printStackTrace()
                })
        )
    }

    fun validateChallenge(){
        addDisposable(
            challengeController.validateChallenge(
                courseId.value.toString(),
                challengeId.value.toString()
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _validateChallenge.postValue(true)
                },{
                    _validateChallenge.postValue(false)
                    it.printStackTrace()
                })
        )
    }


}