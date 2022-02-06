package org.journey.android.presentation.main.challenge.data.dto.today


import com.google.gson.annotations.SerializedName

data class TodayChallengeDTO(
    @SerializedName("afterMent")
    val afterMent: String,
    @SerializedName("badges")
    val badges: List<String>,
    @SerializedName("beforeMent")
    val beforeMent: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("day")
    val day: Int,
    @SerializedName("happy")
    val happy: Int,
    @SerializedName("month")
    val month: String,
    @SerializedName("situation")
    val situation: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: String
)