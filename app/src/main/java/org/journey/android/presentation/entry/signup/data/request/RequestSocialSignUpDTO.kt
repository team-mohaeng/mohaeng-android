package org.journey.android.presentation.entry.signup.data.request


import com.google.gson.annotations.SerializedName

data class RequestSocialSignUpDTO(
    @SerializedName("nickname")
    val nickname: String
)