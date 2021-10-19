package org.journey.android.main.dto


import com.google.gson.annotations.SerializedName

data class ResponseBeforeChallengeDTO(
    @SerializedName("data")
    val data: DataDTO,
    @SerializedName("status")
    val status: Int
)