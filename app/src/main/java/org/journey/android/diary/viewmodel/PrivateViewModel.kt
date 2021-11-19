package org.journey.android.diary.viewmodel

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

    fun getNickname(): String {
        return userPreferenceManager.fetchUserNickName()
    }

}