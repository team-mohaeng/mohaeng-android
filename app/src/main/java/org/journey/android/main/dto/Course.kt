package org.journey.android.main.dto


data class Course(
    val challenges: List<Challenge>,
    val description: String,
    val id: Int,
    val property: String,
    val situation: Int,
    val title: String,
    val totalDays: Int
)