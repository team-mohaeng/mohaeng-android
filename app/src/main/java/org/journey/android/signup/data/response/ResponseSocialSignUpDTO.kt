package org.journey.android.signup.data.response

import com.google.gson.annotations.SerializedName

data class ResponseSocialSignUpDTO(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: Data?,
    val message : String?
){
    data class Data(
        @SerializedName("jwt")
        val jwt: String
    )
}