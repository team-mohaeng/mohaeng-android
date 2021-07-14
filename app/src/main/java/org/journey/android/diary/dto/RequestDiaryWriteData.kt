package org.journey.android.diary.dto

data class RequestDiaryWriteData(
    val moodImage : String,
    val moodText : String,
    val content : String,
    val hashtags : Array<String>,
    val mainimage : String,
    val isPrivate : Boolean
)
