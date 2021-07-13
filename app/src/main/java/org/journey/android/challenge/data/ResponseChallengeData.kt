package org.journey.android.challenge.data

data class ResponseChallengeData(
    val status : Int,
    val success : Boolean,
    val data : List<CourseData>
)

data class CourseData(
    val id : Int,
    val situation : Int,
    val title : String,
    val description : String,
    val totalDays : Int,
    val property : String,
    val challenges : List<ChallengeData>
)

data class ChallengeData(
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
