package org.journey.android.signup.data

import com.google.gson.annotations.SerializedName

data class ResponseSignupDTO(
    @SerializedName("data")
    val dataDTO: DataDTO,
    @SerializedName("status")
    val status: Int
){
    data class DataDTO(
        @SerializedName("jwt")
        val jwt: String
    )
}