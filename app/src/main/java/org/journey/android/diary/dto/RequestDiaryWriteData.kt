package org.journey.android.diary.dto

data class RequestDiaryWriteData(
    val content : String,
    val mainImage : String,
    val mood : String,
    val hashtags : List<String>,
    val isPrivate : Boolean
)
