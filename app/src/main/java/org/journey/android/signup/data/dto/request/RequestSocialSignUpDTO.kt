package org.journey.android.signup.data.dto.request


import com.google.gson.annotations.SerializedName

data class RequestSocialSignUpDTO(
    @SerializedName("nickname")
    val nickname: String
)