package org.journey.android.mypage.data.dto


import com.google.gson.annotations.SerializedName

data class CompleteChallengeDTO(
    @SerializedName("date")
    val date: String,
    @SerializedName("day")
    val day: Int,
    @SerializedName("month")
    val month: String,
    @SerializedName("situation")
    val situation: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: String
)