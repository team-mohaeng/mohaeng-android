package org.journey.android.signup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.base.DisposableViewModel
import org.journey.android.preference.UserPreferenceManager
import org.journey.android.signup.NickNameStatus
import org.journey.android.signup.controller.ChangeNickNameController
import org.journey.android.signup.controller.SignUpController
import org.journey.android.signup.data.RequestChangeNickNameDTO
import org.journey.android.signup.data.RequestSignupDTO
import org.journey.android.signup.data.RequestSocialSignUpDTO
import javax.inject.Inject

@HiltViewModel
class NickNameViewModel @Inject constructor(
    private val signUpController: SignUpController,
    private val changeNickNameController: ChangeNickNameController,
    private val userPreferenceManager: UserPreferenceManager
) : DisposableViewModel() {
    val nickname = MutableLiveData<String>()
    val userEmail = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()
    val userPasswordDoubleCheck = MutableLiveData<String>()
    val changeNickName = MutableLiveData<String>()

    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess : LiveData<Boolean>
        get() = _signUpSuccess

    private val _emailSignUpSuccess = MutableLiveData<Boolean>()
    val emailSignUpSuccess : LiveData<Boolean>
        get() = _emailSignUpSuccess

    private val _nickNameStatus = MutableLiveData<NickNameStatus>()
    val nickNameStatus: LiveData<NickNameStatus>
        get() = _nickNameStatus

    private val _changeNickNameSuccess = MutableLiveData<Boolean>()
    val changeNickNameSuccess : LiveData<Boolean>
        get() = _changeNickNameSuccess

    fun checkNickNameAvailable(){
        nickname.value?.let { nickName ->
            if(nickName.length > 6) { _nickNameStatus.value =
                NickNameStatus.IS_NOT_AVAILABLE_NICKNAME
            }
            else { }
        }
    }

    fun saveEmailSignUpInformation () {
        userEmail.value?.let { userPreferenceManager.saveUserEmail(it) }
        userPassword.value?.let { userPreferenceManager.saveUserPassword(it) }
    }

    fun setNickName(){
        addDisposable(
            signUpController.socialSignUp(
                userPreferenceManager.fetchUserAccessToken(),
                userPreferenceManager.fetchUserFcmDeviceToken(),
                userPreferenceManager.fetchUserSnsType(),
                RequestSocialSignUpDTO(
                    nickname.value.toString()
                )
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    _signUpSuccess.postValue(true)

                },{
                    _signUpSuccess.postValue(false)
                   it.printStackTrace()
                    } )
        )
    }

    fun signUpEmail(){
        addDisposable(
            signUpController.emailSignUp(
                userPreferenceManager.fetchUserFcmDeviceToken(),
                RequestSignupDTO(
                    userPreferenceManager.fetchUserEmail(),
                    nickname.value.toString(),
                    userPreferenceManager.fetchUserPassword()
                )
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    userPreferenceManager.saveUserEmail(userEmail.value.toString())
                    userPreferenceManager.saveUserPassword(userPassword.value.toString())
                    _emailSignUpSuccess.postValue(true)
                },{
                    _emailSignUpSuccess.postValue(false)
                    it.printStackTrace()
                })
        )
    }

    fun changeNickName(){
        addDisposable(
            changeNickNameController.changeNickName(
                RequestChangeNickNameDTO(
                    changeNickName.value.toString()
                )
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _changeNickNameSuccess.postValue(true)
                },{
                    _changeNickNameSuccess.postValue(false)
                    it.printStackTrace()
                })
        )
    }


}