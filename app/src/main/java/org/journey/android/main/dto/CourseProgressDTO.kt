package org.journey.android.main.dto


import com.google.gson.annotations.SerializedName

data class CourseProgressDTO(
    @SerializedName("challengeTitle")
    val challengeTitle: String,
    @SerializedName("percent")
    val percent: Int
)