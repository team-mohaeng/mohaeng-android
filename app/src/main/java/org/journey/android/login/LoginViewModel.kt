package org.journey.android.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.journey.android.base.DisposableViewModel
import org.journey.android.preference.UserPreferenceManager
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userPreferenceManager: UserPreferenceManager
) : DisposableViewModel(){
    private val _accessToken = MutableLiveData<String>()
    private val _kakaoLogin = MutableLiveData<Boolean>()
    private val _googleLogin = MutableLiveData<Boolean>()

    val kakaoLogin: LiveData<Boolean>
        get() = _kakaoLogin

    val googleLogin : LiveData<Boolean>
        get() = _googleLogin

    val accessToken: LiveData<String>
        get() = _accessToken

    private val _refreshToken = MutableLiveData<String>()
    val refreshToken: LiveData<String>
        get() = _refreshToken

    private val _isLoginSuccessed = MutableLiveData<String?>()
    val isLoginSuccessed: LiveData<String?>
        get() = _isLoginSuccessed

    fun changeIsLoginSuccessed(success: String?) {
        _isLoginSuccessed.value = success
    }

    companion object {
        const val LOGIN_SUCCESS = "SUCCESS"
        const val LOGIN_UNAUTHORIZATION = "UNAUTHORIZATION"
        const val LOGIN_FAIL = "FAIL"
    }

}