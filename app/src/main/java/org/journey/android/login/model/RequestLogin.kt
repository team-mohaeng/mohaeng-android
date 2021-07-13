package org.journey.android.login.model

data class RequestLogin(
    val userId: String?,
    val userPw: String?,
    val userToken: String
)