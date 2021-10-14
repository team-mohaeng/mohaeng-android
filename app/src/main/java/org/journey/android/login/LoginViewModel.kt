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

    private val _isLoginSuccessed = MutableLiveData<String>()
    val isLoginSuccessed: LiveData<String>
        get() = _isLoginSuccessed

//    fun socialLogin(accessToken : String, socialType: String){
//        addDisposable(
//            accountController.socialLogin(RequestSocialLoginDTO(accessToken, socialType))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ response ->
//                    if (response.isSuccessful) {
//                        val responseBody = response.body()
//                        if (responseBody!= null) {
//                            _isLoginSuccessed.postValue(LOGIN_SUCCESS)
//                            userPreferenceManager.apply {
//                                val token = "Bearer ${responseBody.token.accessToken}"
//                                saveUserAccessToken(token)
//                                saveUserAccountId(responseBody.account.id)
//                                saveUserRefreshToken(responseBody.token.refreshToken)
//                                saveIsSkipTest(true)
//                                saveIsAlreadyLogIn(true)
//                            }
//                        } else {
//                            userPreferenceManager.saveIsAlreadyLogIn(false)
//                            _isLoginSuccessed.postValue(LOGIN_FAIL)
//                        }
//                    } else {
//                        userPreferenceManager.saveIsAlreadyLogIn(false)
//                        if (response.code() == 500) {
//                            _isLoginSuccessed.postValue(LOGIN_UNAUTHORIZATION)
//                            userPreferenceManager.run {
//                                saveUserAccessToken(accessToken)
//                                saveSocialLoginType(socialType)
//                            }
//                        } else {
//                            _isLoginSuccessed.postValue(LOGIN_FAIL)
//                        }
//                    }
//                },{
//                    it?.printStackTrace()
//                    _isLoginSuccessed.postValue(LOGIN_FAIL)
//                })
//        )
//    }

    companion object {
        const val LOGIN_SUCCESS = "SUCCESS"
        const val LOGIN_UNAUTHORIZATION = "UNAUTHORIZATION"
        const val LOGIN_FAIL = "FAIL"
    }

}