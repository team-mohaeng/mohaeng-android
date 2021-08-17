package org.journey.android.preference

class UserPreferenceManager(private val sharedPreferencesManager: SharedPreferencesManager) {

    fun setUserAccessToken(token: String) {
        sharedPreferencesManager.putSharedPref(ACCESS_TOKEN, token)
    }

    fun getUserAccessToken(): String {
        return sharedPreferencesManager.getSharedPrefs(ACCESS_TOKEN, "")
    }

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
    }
}
