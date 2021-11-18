package org.journey.android.challenge.data.dto.validate


import com.google.gson.annotations.SerializedName

data class ValidateChallengeCompletionDTO(
    @SerializedName("fullHappy")
    val fullHappy: Int,
    @SerializedName("happy")
    val happy: Int,
    @SerializedName("isPenalty")
    val isPenalty: Boolean,
    @SerializedName("userHappy")
    val userHappy: Int
)