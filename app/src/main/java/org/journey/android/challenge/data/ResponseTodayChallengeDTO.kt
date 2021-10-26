package org.journey.android.challenge.data


import com.google.gson.annotations.SerializedName

data class ResponseTodayChallengeDTO(
    @SerializedName("data")
    val todayChallengeDataDTO: TodayChallengeDataDTO,
    @SerializedName("status")
    val status: Int
)