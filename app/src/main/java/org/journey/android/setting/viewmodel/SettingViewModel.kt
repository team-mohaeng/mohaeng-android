package org.journey.android.setting.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import org.journey.android.R
import org.journey.android.base.DisposableViewModel
import org.journey.android.preference.UserPreferenceManager
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val userPreferenceManager: UserPreferenceManager
) : DisposableViewModel(){
    private val _accessToken = MutableLiveData<String>()

    private val _fcmDeviceToken = MutableLiveData<String>()
    val fcmDeviceToken: LiveData<String>
        get() = _fcmDeviceToken

    val accessToken: LiveData<String>
        get() = _accessToken

    private val _refreshToken = MutableLiveData<String>()
    val refreshToken: LiveData<String>
        get() = _refreshToken

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

    fun removeFcmDeviceToken(){
        userPreferenceManager.saveUserFcmDeviceToken("")
    }

    fun logout(): String {
        userPreferenceManager.saveUserRefreshToken("")
        userPreferenceManager.saveUserEmail("")
        userPreferenceManager.saveUserPassword("")
        userPreferenceManager.saveUserAccessToken("")

        return userPreferenceManager.fetchUserSnsType()
    }

    fun getJWT(): String {
        return userPreferenceManager.fetchUserAccessToken()
    }

}