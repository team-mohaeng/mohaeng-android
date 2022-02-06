package org.journey.android.presentation.entry.login.data.response.social


import com.google.gson.annotations.SerializedName

data class ResponseGoogleSignInDTO(
    @SerializedName("data")
    val data: Data,
    @SerializedName("status")
    val status: Int
){
    data class Data(
        @SerializedName("jwt")
        val jwt: String?,
        @SerializedName("user")
        val user: Boolean
    )
}