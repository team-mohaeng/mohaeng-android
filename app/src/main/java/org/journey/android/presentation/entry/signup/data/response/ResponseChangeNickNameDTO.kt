package org.journey.android.presentation.entry.signup.data.response


import com.google.gson.annotations.SerializedName

data class ResponseChangeNickNameDTO(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)