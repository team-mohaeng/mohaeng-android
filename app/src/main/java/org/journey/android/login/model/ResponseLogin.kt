package org.journey.android.login.model


data class ResponseLogin(
    val status: Int,
    val data: LoginData?
) {
    data class LoginData(
        val jwt: String
    )
}