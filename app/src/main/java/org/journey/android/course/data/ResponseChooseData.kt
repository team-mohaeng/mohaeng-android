package org.journey.android.course.data

data class ResponseChooseData(
    val status: Int,
    val data: ChooseDatasData?
)

data class ChooseDatasData(
    val course: ChooseCourseData?
)

data class ChooseCourseData(
    val id: Int,
    val situation: Int,
    val title: String,
    val description: String,
    val totalDays: Int,
    val property: Int,
    val challenges: List<ChooseChallengesData>
)

data class ChooseChallengesData(
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
