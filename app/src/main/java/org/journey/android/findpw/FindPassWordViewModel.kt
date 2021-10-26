package org.journey.android.findpw

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.base.BaseViewModel
import org.journey.android.base.DisposableViewModel
import org.journey.android.findpw.controller.SendVerificationController
import javax.inject.Inject

@HiltViewModel
class FindPassWordViewModel @Inject constructor(
    private val sendVerificationController: SendVerificationController
) : DisposableViewModel(){
    val userEmail = MutableLiveData<String>("")

    private val _sendVerificationCode = MutableLiveData<Boolean>()
    val sendVerificationCode : LiveData<Boolean>
        get() = _sendVerificationCode

    fun sendVericiationCode() {
        addDisposable(
            sendVerificationController.sendVerification(
                userEmail.value.toString()
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                       _sendVerificationCode.postValue(true)
                },{
                    _sendVerificationCode.postValue(false)
                    it.printStackTrace()
                })
        )
    }

}