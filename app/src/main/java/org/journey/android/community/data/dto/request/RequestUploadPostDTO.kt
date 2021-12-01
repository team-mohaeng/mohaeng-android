package org.journey.android.community.data.dto.request


data class RequestUploadPostDTO(
    val content: String,
    val isPrivate: Boolean,
    val mood: Int
)