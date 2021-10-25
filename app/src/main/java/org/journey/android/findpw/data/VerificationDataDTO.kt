package org.journey.android.findpw.data


import com.google.gson.annotations.SerializedName

data class VerificationDataDTO(
    @SerializedName("number")
    val number: String
)