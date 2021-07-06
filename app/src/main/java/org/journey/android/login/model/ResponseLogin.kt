package org.journey.android.login.model

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    val success : Boolean,
    val message: String,
    val data: LoginData?
) {
    data class LoginData(
        @SerializedName("UserId")
        val userId: Int,
        @SerializedName("user_nickname")
        val userNickname: String,
        val token: String
    )
}