package org.journey.android.presentation.main.course.data.dto


import com.google.gson.annotations.SerializedName

data class StartChallengeDTO(
    @SerializedName("course")
    val startCourseDTO: StartCourseDTO,
    @SerializedName("isComplete")
    val isComplete: Boolean,
    @SerializedName("isPenalty")
    val isPenalty: Boolean,
    @SerializedName("mainCharacterImg")
    val mainCharacterImg: String,
    @SerializedName("popupCharacterImg")
    val popupCharacterImg: String
)