package org.journey.android.findpw


import com.google.gson.annotations.SerializedName

data class RequestChangePasswordDTO(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)