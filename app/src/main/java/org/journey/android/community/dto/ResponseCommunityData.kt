package org.journey.android.community.dto

data class ResponseCommunityData(
    val status: Int,
    val data: Data?
){
    data class Data(
        val community: List<Community>,
        val hasSmallSatisfaction: Int,
        val userCount: Int
    ){
        data class Community(
            val content: String,
            val day: String,
            val hasLike: Boolean,
            val hashtags: List<String>,
            val likeCount: Int,
            val mainImage: String,
            val month: String,
            val mood: Int,
            val nickname: String,
            val week: String,
            val year: String
        )
    }
}