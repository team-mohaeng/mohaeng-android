package org.journey.android.login.data


import com.google.gson.annotations.SerializedName

data class RequestEmailSignInDTO(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)