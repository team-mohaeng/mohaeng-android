package org.journey.android.preference


class UserPreferenceManager(private val sharedPreferencesManager: SharedPreferencesManager) {
    fun saveUserFcmDeviceToken(token: String?) = sharedPreferencesManager.putSharedPref(FCM_DEVICE_TOKEN, token)
    fun fetchUserFcmDeviceToken(): String = sharedPreferencesManager.getSharedPrefs(FCM_DEVICE_TOKEN, "")

    fun saveUserAccessToken(token: String?) = sharedPreferencesManager.putSharedPref(ACCESS_TOKEN, token)
    fun fetchUserAccessToken(): String = sharedPreferencesManager.getSharedPrefs(ACCESS_TOKEN, "")

    fun saveUserSnsType(snsType : String) = sharedPreferencesManager.putSharedPref(SNS_TYPE, snsType)
    fun fetchUserSnsType() : String = sharedPreferencesManager.getSharedPrefs(SNS_TYPE, "")

    fun saveUserRefreshToken(token: String?) = sharedPreferencesManager.putSharedPref(REFRESH_TOKEN, token)
    fun fetchUserRefreshToken(): String = sharedPreferencesManager.getSharedPrefs(REFRESH_TOKEN, "")

    fun saveUserEmail(email: String) { sharedPreferencesManager.putSharedPref(USER_EMAIL, email) }
    fun fetchUserEmail(): String { return sharedPreferencesManager.getSharedPrefs(USER_EMAIL, "") }

    fun saveUserPassword(password : String) = sharedPreferencesManager.putSharedPref(PASS_WORD, password)
    fun fetchUserPassword() : String { return sharedPreferencesManager.getSharedPrefs(PASS_WORD, "")}

    fun saveUserNickName(nickname : String) = sharedPreferencesManager.putSharedPref(NICK_NAME, nickname)
    fun fetchUserNickName() : String { return sharedPreferencesManager.getSharedPrefs(NICK_NAME, "")}

    fun saveIsAlreadyLogIn(isAlreadyLogIn: Boolean) = sharedPreferencesManager.putSharedPref(IS_ALREADY_LOGIN, isAlreadyLogIn)
    fun fetchIsAlreadyLogIn(): Boolean = sharedPreferencesManager.getSharedPrefs(IS_ALREADY_LOGIN, false)


    fun saveCheckEmail(checkEmail: String) { sharedPreferencesManager.putSharedPref(CHECK_EMAIL, checkEmail) }
    fun fetchCheckEmail(): String { return sharedPreferencesManager.getSharedPrefs(CHECK_EMAIL, "") }

    fun saveCheckPassword(checkPassword : String) = sharedPreferencesManager.putSharedPref(CHECK_PASSWORD, checkPassword)
    fun fetchCheckPassword() : String { return sharedPreferencesManager.getSharedPrefs(CHECK_PASSWORD, "")}

    fun saveCheckDoublePassword(checkDoublePassword : String) = sharedPreferencesManager.putSharedPref(CHECK_DOUBLE_PASSWORD, checkDoublePassword)
    fun fetchCheckDoublePassword() : String { return sharedPreferencesManager.getSharedPrefs(CHECK_DOUBLE_PASSWORD, "")}

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
        private const val USER_EMAIL = "user_email"
        private const val FCM_DEVICE_TOKEN = "fcm_device_token"
        private const val SNS_TYPE = "sns_type"
        private const val PASS_WORD = "pass_word"
        private const val NICK_NAME = "nick_name"
        private const val IS_ALREADY_LOGIN = "is_already_login"
        private const val CHECK_EMAIL = "check_email"
        private const val CHECK_PASSWORD = "check_password"
        private const val CHECK_DOUBLE_PASSWORD = "check_double_password"
    }
}
