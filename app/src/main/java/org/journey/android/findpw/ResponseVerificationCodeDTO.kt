package org.journey.android.findpw


import com.google.gson.annotations.SerializedName

data class ResponseVerificationCodeDTO(
    @SerializedName("data")
    val verificationDataDTO: VerificationDataDTO,
    @SerializedName("status")
    val status: Int
)