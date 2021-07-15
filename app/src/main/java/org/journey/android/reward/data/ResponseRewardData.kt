package org.journey.android.reward.data

import org.journey.android.course.data.ChallengesLibraryData

data class ResponseRewardData(
    val status : String,
    val data : RewardData?
)

data class RewardData(
    val totalIncreasedAffinity : Int,
    val maxSuccessCount : Int,
    val courses : List<RewardCoursesData>
)

data class RewardCoursesData(
    val id: Int,
    val situation: Int,
    val title: String,
    val description: String,
    val totalDays: Int,
    val property: Int,
    val challenges: List<RewardChallengesData>
)

data class RewardChallengesData(
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
