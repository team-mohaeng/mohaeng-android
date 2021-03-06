package org.journey.android.presentation.entry.login.data.response.social


import com.google.gson.annotations.SerializedName

data class ResponseKakaoSignInDTO(
    @SerializedName("status")
    val status: Int,
    val data: TokenDTO?
) {
    data class TokenDTO(
        val user : Boolean,
        val jwt: String
    )
}