package org.journey.android.diary.dto

data class RequestDiaryWriteData(
    val mood : Int,
    val content : String,
    val hashtags : List<String>,
    val mainImage : String,
    val isPrivate : Boolean
)
