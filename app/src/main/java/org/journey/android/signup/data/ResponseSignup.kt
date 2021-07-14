package org.journey.android.signup.data

data class ResponseSignup(
    val status : Int,
    val data : SignupData?
)

data class  SignupData(
    val jwt: String
)