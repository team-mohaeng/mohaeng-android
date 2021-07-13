package org.journey.android.signup

data class RequestSignup(
    val userId: String,
    val userPw: String,
    val nickname: String,
    val gender: Int,
    val birthYear: Int
)