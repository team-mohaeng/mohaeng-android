package org.journey.android.main.dto


import com.google.gson.annotations.SerializedName

data class ResponseOngoingChallengeDTO(
    @SerializedName("data")
    val challengeProgressDTO: ChallengeProgressDTO,
    @SerializedName("status")
    val status: Int
)