package org.journey.android.preference

class UserPreferenceManager(private val sharedPreferencesManager: SharedPreferencesManager) {

    fun saveUserFcmDeviceToken(token: String?) = sharedPreferencesManager.putSharedPref(FCM_DEVICE_TOKEN, token)
    fun fetchUserFcmDeviceToken(): String = sharedPreferencesManager.getSharedPrefs(FCM_DEVICE_TOKEN, "")

    fun saveUserAccessToken(token: String?) = sharedPreferencesManager.putSharedPref(ACCESS_TOKEN, token)
    fun fetchUserAccessToken(): String = sharedPreferencesManager.getSharedPrefs(ACCESS_TOKEN, "")

    fun saveUserRefreshToken(token: String?) = sharedPreferencesManager.putSharedPref(REFRESH_TOKEN, token)
    fun fetchUserRefreshToken(): String = sharedPreferencesManager.getSharedPrefs(REFRESH_TOKEN, "")

    fun saveUserEmail(email: String) { sharedPreferencesManager.putSharedPref(USER_EMAIL, email) }
    fun fetchUserEmail(): String { return sharedPreferencesManager.getSharedPrefs(USER_EMAIL, "") }

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
        private const val USER_EMAIL = "user_email"
        private const val FCM_DEVICE_TOKEN = "fcm_device_token"
    }
}
