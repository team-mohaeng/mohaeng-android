package org.journey.android.challenge.data

import org.journey.android.course.data.ChooseCourseData

data class ResponseChallengeData(
    val status: Int,
    val data: ChallengeDatasData?
)

data class ChallengeDatasData(
    val course: CourseData?
)

data class CourseData(
    val id: Int,
    val situation: Int,
    val title: String,
    val description: String,
    val totalDays: Int,
    val property: Int,
    val challenges: List<ChallengeData>
)

data class ChallengeData(
    val id : Int,
    val title : String,
    val situation : Int,
    val description : String,
    val successDescription : String,
    val year : String,
    val month : String,
    val day : String,
    val currentStamp : Int,
    val totalStamp : Int,
    val userMents : List<String>
)
