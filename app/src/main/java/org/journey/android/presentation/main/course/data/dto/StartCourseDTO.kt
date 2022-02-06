package org.journey.android.presentation.main.course.data.dto


import com.google.gson.annotations.SerializedName

data class StartCourseDTO(
    @SerializedName("challenges")
    val challengeDTOS: List<ChallengeDTO>,
    @SerializedName("currentDay")
    val currentDay: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("month")
    val month: String,
    @SerializedName("property")
    val property: Int,
    @SerializedName("situation")
    val situation: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("totalDays")
    val totalDays: Int,
    @SerializedName("year")
    val year: String
)