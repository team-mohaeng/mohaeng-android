package org.journey.android.diary.dto

data class PrivateData(
    val postId: Int,
    val textViewHashTags: String,
    val textViewPrivateContent: String,
    val textViewPrivateNickName: String,
    val imageViewPrivate: String,
    val textViewLikeCount: String,
    val hasLike : Boolean
)