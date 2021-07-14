package org.journey.android.main.dto

import org.journey.android.main.dto.Data

data class ResponseMainModelItem(
    val status: Int,
    val data: MainData?
)

data class MainData(
    val situation: Int,
    val affinity: Int,
    val course: MainCourse?
)

data class MainCourse(
    val id: Int,
    val situation: Int,
    val title: String,
    val description: String,
    val totalDays: Int,
    val property: Int,
    val challenges: List<MainChallenge>
)

data class MainChallenge(
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