package org.journey.android.presentation.main.challenge.data.dto.validate


import com.google.gson.annotations.SerializedName

data class ValidateCourseCompletionDTO(
    @SerializedName("fullHappy")
    val fullHappy: Int,
    @SerializedName("happy")
    val happy: Int,
    @SerializedName("property")
    val property: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userHappy")
    val userHappy: Int
)