package org.journey.android.presentation.main.challenge.data.response


import com.google.gson.annotations.SerializedName
import org.journey.android.presentation.main.challenge.data.dto.today.TodayChallengeDataDTO

data class ResponseTodayChallengeDTO(
    @SerializedName("data")
    val todayChallengeDataDTO: TodayChallengeDataDTO,
    @SerializedName("status")
    val status: Int
)