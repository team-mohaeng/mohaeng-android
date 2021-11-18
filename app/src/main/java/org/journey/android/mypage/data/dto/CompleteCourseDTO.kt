package org.journey.android.mypage.data.dto


import com.google.gson.annotations.SerializedName

data class CompleteCourseDTO(
    @SerializedName("challenges")
    val completeChallengeDTOS: List<CompleteChallengeDTO>,
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("index")
    val index: Int,
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