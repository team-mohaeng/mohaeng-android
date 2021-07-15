package org.journey.android.diary.dto

import java.io.File

data class RequestDiaryWriteData(
    val content: String,
    val mainImage: String,
    val mood: String,
    val hashtags: List<String>,
    val isPrivate: Boolean
)
