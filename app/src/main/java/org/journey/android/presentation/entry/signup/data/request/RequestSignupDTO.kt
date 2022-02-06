package org.journey.android.presentation.entry.signup.data.request

import com.google.gson.annotations.SerializedName

data class RequestSignupDTO(
    @SerializedName("email")
    val email: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("password")
    val password: String
)