package org.journey.android.data

data class ResponseStampData(
    val status: Int,
    val data: StampDatasData?
)

data class StampDatasData(
    val course: StampCourseData?
)

data class StampCourseData(
    val id: Int,
    val situation: Int,
    val title: String,
    val description: String,
    val totalDays: Int,
    val property: Int,
    val challenges: List<StampChallengeData>
)

data class StampChallengeData(
    val id: Int,
    val title: String,
    val situation: Int,
    val description: String,
    val year: String,
    val month: String,
    val day: String,
    val currentStamp: Int,
    val totalStamp: Int,
    val userMents: List<String>
)