package org.journey.android.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.base.DisposableViewModel
import org.journey.android.login.controller.SignInController
import org.journey.android.login.data.request.RequestEmailSignInDTO
import org.journey.android.network.dto.ResponseAuthDTO
import org.journey.android.preference.UserPreferenceManager
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userPreferenceManager: UserPreferenceManager,
    private val signInController: SignInController
) : DisposableViewModel(){
    private val _accessToken = MutableLiveData<String>()
    private val _kakaoLogin = MutableLiveData<Boolean>()
    private val _googleLogin = MutableLiveData<Boolean>()

    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    private val _fcmDeviceToken = MutableLiveData<String>()
    val fcmDeviceToken: LiveData<String>
        get() = _fcmDeviceToken

    val kakaoLogin: LiveData<Boolean>
        get() = _kakaoLogin

    val googleLogin : LiveData<Boolean>
        get() = _googleLogin

    val accessToken: LiveData<String>
        get() = _accessToken

    private val _refreshToken = MutableLiveData<String>()
    val refreshToken: LiveData<String>
        get() = _refreshToken

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

    private val _isLoginSuccessed = MutableLiveData<String?>()
    val isLoginSuccessed: LiveData<String?>
        get() = _isLoginSuccessed

    fun signIn() {
        addDisposable(
            signInController.emailSingIn(
                userPreferenceManager.fetchUserFcmDeviceToken(),
                RequestEmailSignInDTO(
                    email.value.toString(),
                    password.value.toString()
                )
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    userPreferenceManager.saveUserAccessToken(response.data?.jwt.toString())
                    _loginSuccess.postValue(true)
                }, {
                    it.printStackTrace()
                    _loginSuccess.postValue(false)
                    _isLoginSuccessed.postValue(LOGIN_UNAUTHORIZATION)
                })
        )
    }

    fun kakaoLogin() {
        addDisposable(
            signInController.kakaoSignIn(
                userPreferenceManager.fetchUserAccessToken(),
                userPreferenceManager.fetchUserFcmDeviceToken(),
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.data?.user == true) {
                        it.data?.jwt?.let { userPreferenceManager.saveUserAccessToken(it) }
                        _loginSuccess.postValue(true)
                    } else {
                        _loginSuccess.postValue(false)
                    }
                    userPreferenceManager.saveUserSnsType(snsType = "kakao")
                },{
                    _loginSuccess.postValue(false)
                    it.printStackTrace()
                })
        )
    }

    fun googleLogin(){
        addDisposable(
            signInController.googleSignIn(
                userPreferenceManager.fetchUserAccessToken(),
                userPreferenceManager.fetchUserFcmDeviceToken()
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.data?.jwt?.let { userPreferenceManager.saveUserAccessToken(it) }
                    userPreferenceManager.saveUserSnsType(snsType = "google")
                    _loginSuccess.postValue(true)
                },{
                    _loginSuccess.postValue(false)
                    _isLoginSuccessed.postValue(LOGIN_FAIL)
                    it.printStackTrace()
                })
        )
    }

    fun getFcmDeviceToken() {
        FirebaseMessaging.getInstance()
            .token.addOnCompleteListener(OnCompleteListener { task ->
                if(!task.isSuccessful) {
                    return@OnCompleteListener
                }
                val token = task.result

                Log.e("token", "$token")
                _fcmDeviceToken.postValue(token)
                userPreferenceManager.saveUserFcmDeviceToken(token)
            })
    }

    fun saveAccessToken(token: String) {
        userPreferenceManager.saveUserAccessToken(token)
    }

    private fun loginSuccess(response : ResponseAuthDTO) {
        _isLoginSuccessed.postValue(LOGIN_SUCCESS)
        userPreferenceManager.apply {
            saveUserAccessToken(response.accessToken)
            saveUserEmail(response.userEmail)
            saveUserRefreshToken(response.refreshToken)
            saveIsAlreadyLogIn(true)
        }
    }

    companion object {
        const val LOGIN_SUCCESS = "SUCCESS"
        const val LOGIN_UNAUTHORIZATION = "UNAUTHORIZATION"
        const val LOGIN_FAIL = "FAIL"
    }
}