package org.journey.android.presentation.main.diary.dto

data class ResponseDiaryWriteData(
    val success: Boolean,
    val message: String,
    val data : DiaryWriteData?
    ){
        data class DiaryWriteData(
            val image: String
    )
}

