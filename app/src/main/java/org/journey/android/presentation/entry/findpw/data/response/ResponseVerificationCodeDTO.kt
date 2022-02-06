package org.journey.android.presentation.entry.findpw.data.response


import com.google.gson.annotations.SerializedName

data class ResponseVerificationCodeDTO(
    @SerializedName("data")
    val verificationDataDTO: VerificationDataDTO,
    @SerializedName("status")
    val status: Int
){
    data class VerificationDataDTO(
        @SerializedName("number")
        val number: String
    )
}