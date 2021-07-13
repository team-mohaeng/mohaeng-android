package org.journey.android.course.data

data class ResponseLibraryData(
    val status : Int,
    val data : CourseLibraryData?
)

data class  CourseLibraryData(
    val courses: List<CoursesLibraryData>
)

data class CoursesLibraryData(
    val id : Int,
    val situation : Int,
    val title : String,
    val description : String,
    val totalDays : Int,
    val property : String,
    val challenges : List<ChallengesLibraryData>
)

data class ChallengesLibraryData(
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