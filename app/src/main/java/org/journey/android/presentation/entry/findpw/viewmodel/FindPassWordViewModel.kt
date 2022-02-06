package org.journey.android.presentation.entry.findpw.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.presentation.base.DisposableViewModel
import org.journey.android.presentation.entry.findpw.controller.modify.ChangePasswordController
import org.journey.android.presentation.entry.findpw.controller.verification.SendVerificationController
import org.journey.android.presentation.entry.findpw.data.request.RequestChangePasswordDTO
import org.journey.android.presentation.preference.UserPreferenceManager
import javax.inject.Inject

@HiltViewModel
class FindPassWordViewModel @Inject constructor(
    private val userPreferenceManager: UserPreferenceManager,
    private val sendVerificationController: SendVerificationController,
    private val changePasswordController: ChangePasswordController
) : DisposableViewModel(){
    val userEmail = MutableLiveData<String>()
    val newPassword = MutableLiveData<String>("")

    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    private val _sendVerificationCode = MutableLiveData<Boolean>()
    val sendVerificationCode : LiveData<Boolean>
        get() = _sendVerificationCode

    private val _changePassword = MutableLiveData<Boolean>()
    val changePassword : LiveData<Boolean>
        get() = _changePassword

    fun sendVericiationCode() {
        addDisposable(
            sendVerificationController.sendVerification(
                userEmail.value.toString()
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                       _sendVerificationCode.postValue(true)
                    saveUserEmail(userEmail.value.toString())
                },{
                    _sendVerificationCode.postValue(false)
                    it.printStackTrace()
                })
        )
    }

    fun changePassword(){
        addDisposable(
            changePasswordController.changePassword(
                RequestChangePasswordDTO(
                    userPreferenceManager.fetchUserEmail(),
                    newPassword.value.toString()
                )
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                     _changePassword.postValue(true)
                },{
                    _changePassword.postValue(false)
                    it.printStackTrace()
                })
        )
    }

    fun saveUserEmail(userEmail : String){
        userPreferenceManager.saveUserEmail(userEmail)
    }

}