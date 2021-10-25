package org.journey.android.login.data

import com.google.gson.annotations.SerializedName

data class ResponseEmailSignInDTO(
    @SerializedName("status")
    val status: Int,
    val data: TokenDTO?
) {
    data class TokenDTO(
        val jwt: String
    )
}

