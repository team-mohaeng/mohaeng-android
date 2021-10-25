package org.journey.android.signup.data


import com.google.gson.annotations.SerializedName

data class RequestSocialSignUpDTO(
    @SerializedName("nickname")
    val nickname: String
)