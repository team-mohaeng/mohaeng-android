package org.journey.android.main.dto

data class Challenge(
    val currentStamp: Int,
    val day: String,
    val description: String,
    val id: Int,
    val month: String,
    val situation: Int,
    val title: String,
    val totalStamp: Int,
    val userMents: List<String>,
    val year: String
)