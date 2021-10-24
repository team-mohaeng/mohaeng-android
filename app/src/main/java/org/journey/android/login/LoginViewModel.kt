package org.journey.android.login

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
import org.journey.android.login.data.RequestEmailSignInDTO
import org.journey.android.login.data.ResponseEmailSignInDTO
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

    init {

    }

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
                    _loginSuccess.postValue(true)
                }, {
                    it.printStackTrace()
                    _loginSuccess.postValue(false)
                    Log.e("email signin fail","signin email fail")
                })
        )
    }

    fun kakaoLogin(header: String) {
        addDisposable(
            signInController.kakaoSignIn(header)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.data?.jwt?.let { userPreferenceManager.saveUserAccessToken(it) }
                    _loginSuccess.postValue(true)
                },{
                    _loginSuccess.postValue(false)
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



    companion object {
        const val LOGIN_SUCCESS = "SUCCESS"
        const val LOGIN_UNAUTHORIZATION = "UNAUTHORIZATION"
        const val LOGIN_FAIL = "FAIL"
    }

}