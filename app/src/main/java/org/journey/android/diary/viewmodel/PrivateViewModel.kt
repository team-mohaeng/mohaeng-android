package org.journey.android.diary.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import org.journey.android.base.DisposableViewModel
import org.journey.android.preference.UserPreferenceManager
import javax.inject.Inject

@HiltViewModel
class PrivateViewModel @Inject constructor(
    private val userPreferenceManager: UserPreferenceManager
) : DisposableViewModel(){

    fun getJWT(): String {
        return userPreferenceManager.fetchUserAccessToken()
    }

}