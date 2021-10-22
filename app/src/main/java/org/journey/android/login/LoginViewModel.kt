package org.journey.android.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
        addDisposable( signInController.emailSingIn(
                RequestEmailSignInDTO(
                    email.value.toString(),
                    password.value.toString()
                )
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    successSignIn(response)
                }, {
                    it.printStackTrace()
                    Log.e("email signin fail","signin email fail")
                })
        )
    }

    private fun successSignIn(response: ResponseEmailSignInDTO) {
        userPreferenceManager.apply {
            saveUserEmail(response.email)
        }
        _loginSuccess.postValue(true)
    }





    companion object {
        const val LOGIN_SUCCESS = "SUCCESS"
        const val LOGIN_UNAUTHORIZATION = "UNAUTHORIZATION"
        const val LOGIN_FAIL = "FAIL"
    }

}