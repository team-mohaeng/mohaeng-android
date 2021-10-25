package org.journey.android.findpw.data


import com.google.gson.annotations.SerializedName

data class ResponseVerificationCodeDTO(
    @SerializedName("data")
    val verificationDataDTO: VerificationDataDTO,
    @SerializedName("status")
    val status: Int
)