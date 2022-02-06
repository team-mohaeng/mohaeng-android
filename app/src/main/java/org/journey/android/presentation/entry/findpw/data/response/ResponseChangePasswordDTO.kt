package org.journey.android.presentation.entry.findpw.data.response


import com.google.gson.annotations.SerializedName

data class ResponseChangePasswordDTO(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)