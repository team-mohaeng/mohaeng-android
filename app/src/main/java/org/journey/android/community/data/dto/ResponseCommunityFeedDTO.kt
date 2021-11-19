package org.journey.android.community.data.dto


import com.google.gson.annotations.SerializedName
import org.journey.android.community.data.entity.CommunityPostEntity

data class ResponseCommunityFeedDTO(
    @SerializedName("data")
    val data: Data,
    @SerializedName("status")
    val status: Int,
    val message : String?
){
    data class Data(
        @SerializedName("feeds")
        val feeds: List<Feed>,
        @SerializedName("hasFeed")
        val hasFeed: Int,
        @SerializedName("isNew")
        val isNew: Boolean,
        @SerializedName("userCount")
        val userCount: Int
    ){
        data class Feed(
            @SerializedName("challenge")
            val challenge: Int,
            @SerializedName("content")
            val content: String,
            @SerializedName("course")
            val course: String,
            @SerializedName("date")
            val date: String,
            @SerializedName("day")
            val day: String,
            @SerializedName("emoji")
            val emoji: List<Emoji>,
            @SerializedName("image")
            val image: String,
            @SerializedName("isDelete")
            val isDelete: Boolean,
            @SerializedName("isReport")
            val isReport: Boolean,
            @SerializedName("month")
            val month: String,
            @SerializedName("mood")
            val mood: Int,
            @SerializedName("myEmoji")
            val myEmoji: Int,
            @SerializedName("nickname")
            val nickname: String,
            @SerializedName("postId")
            val postId: Int,
            @SerializedName("year")
            val year: String
        ){
            data class Emoji(
                @SerializedName("count")
                val count: Int,
                @SerializedName("id")
                val id: Int
            )
            fun convertToCommunityPostEntity() : CommunityPostEntity {
                return CommunityPostEntity(
                    image,
                    course,
                    content,
                    nickname,
                    month,
                    date
                )
            }
        }
    }
}