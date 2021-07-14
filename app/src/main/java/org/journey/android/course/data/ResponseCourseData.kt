package org.journey.android.course.data

data class ResponseCourseData(
    val status : Int,
    val data : CourseDatasData?
)

data class  CourseDatasData(
    val course: CoursesData?
)

data class CoursesData(
    val id : Int,
    val situation : Int,
    val title : String,
    val description : String,
    val totalDays : Int,
    val property : String,
    val challenges : List<ChallengesData>
)

data class ChallengesData(
    val id : Int,
    val title : String,
    val situation : Int,
    val description : String,
    val year : String,
    val month : String,
    val day : String,
    val currentStamp : Int,
    val totalStamp : Int,
    val userMents : List<String>
)