package org.journey.android.diary.dto

data class ResponseDiaryPrivateDetailData(
    val success: Boolean,
    val message: String,
    val data: DiaryPrivateData?
) {
    data class DiaryPrivateData(
        val nickname: String,
        val postId: Int,
        val mainImage: String,
        val mood: Int,
        val hashtags: List<String>,
        val content: String,
        val likeCount: Int,
        val hasLike: Boolean,
        val year: String,
        val month: String,
        val day: String,
        val week: String
    )
}

