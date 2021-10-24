package org.journey.android.login.data


import com.google.gson.annotations.SerializedName

data class ResponseKakaoSignInDTO(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)