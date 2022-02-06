package org.journey.android.presentation.main.challenge.data.dto.today


import com.google.gson.annotations.SerializedName

data class TodayChallengeDataDTO(
    @SerializedName("course")
    val todayCourseDTO: TodayCourseDTO?,
    @SerializedName("isComplete")
    val isComplete: Boolean,
    @SerializedName("isPenalty")
    val isPenalty: Boolean,
    @SerializedName("mainCharacterImg")
    val mainCharacterImg: String,
    @SerializedName("popupCharacterImg")
    val popupCharacterImg: String
)