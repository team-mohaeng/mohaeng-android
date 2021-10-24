package org.journey.android.findpw


import com.google.gson.annotations.SerializedName

data class VerificationDataDTO(
    @SerializedName("number")
    val number: String
)