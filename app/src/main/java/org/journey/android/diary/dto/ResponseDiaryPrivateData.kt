package org.journey.android.diary.dto

data class ResponseDiaryPrivateData(
    val success: Boolean,
    val data: Data?
) {
    data class Data(
        val myDrawerSmallSatisfactions: List<MyDrawerSmallSatisfaction>?
    ) {
        data class MyDrawerSmallSatisfaction(
            val content: String,
            val day: String,
            val hasLike: Boolean,
            val hashtags: List<String>,
            val likeCount: Int,
            val mainImage: String,
            val month: String,
            val moodImage: String,
            val nickname: String,
            val postId: Int,
            val year: String
        )
    }
}