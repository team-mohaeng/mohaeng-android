package org.journey.android.presentation.entry.signup.data.response

import com.google.gson.annotations.SerializedName

data class ResponseSignupDTO(
    @SerializedName("data")
    val dataDTO: DataDTO,
    @SerializedName("status")
    val status: Int,
    val message : String?
){
    data class DataDTO(
        @SerializedName("jwt")
        val jwt: String
    )
}