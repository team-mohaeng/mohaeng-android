package org.journey.android.presentation.main.course.data.dto


import com.google.gson.annotations.SerializedName

data class ResponseStartChallengeDTO(
    @SerializedName("data")
    val startChallengeDTO: StartChallengeDTO,
    @SerializedName("status")
    val status: Int
)