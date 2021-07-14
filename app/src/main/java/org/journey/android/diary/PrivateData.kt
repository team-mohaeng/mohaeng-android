package org.journey.android.diary

data class PrivateData(
    val postId: Int,
    val textViewHashTags: String,
    val textViewPrivateContent: String,
    val textViewPrivateNickName: String,
    val imageViewPrivate: String,
    val textViewLikeCount: String
)