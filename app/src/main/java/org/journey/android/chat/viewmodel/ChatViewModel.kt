package org.journey.android.chat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.base.BaseViewModel
import org.journey.android.base.DisposableViewModel
import org.journey.android.chat.controller.ChatController
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatController: ChatController
) : DisposableViewModel() {
    private val _pushAlarmSuccess = MutableLiveData<Boolean>()
    val pushAlarmSuccess : LiveData<Boolean>
        get() = _pushAlarmSuccess

    fun getPushAlarmChatMessage() {
        addDisposable(
            chatController.pushAlarm()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ fcm ->
                    _pushAlarmSuccess.postValue(true)

                },{
                    _pushAlarmSuccess.postValue(false)
                    it.printStackTrace()

                })
        )
    }

}