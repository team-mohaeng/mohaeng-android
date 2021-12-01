package org.journey.android.challenge.data.response


import com.google.gson.annotations.SerializedName
import org.journey.android.challenge.data.dto.validate.ValidateChallengeDataDTO

data class ResponseValidateChallengeDTO(
    @SerializedName("data")
    val validateChallengeDataDTO: ValidateChallengeDataDTO,
    @SerializedName("status")
    val status: Int
)