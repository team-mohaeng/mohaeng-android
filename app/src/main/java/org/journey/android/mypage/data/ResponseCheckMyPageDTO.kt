package org.journey.android.mypage.data


import com.google.gson.annotations.SerializedName

data class ResponseCheckMyPageDTO(
    @SerializedName("data")
    val data: Data,
    @SerializedName("status")
    val status: Int
){
    data class Data(
        @SerializedName("badgeCount")
        val badgeCount: Int,
        @SerializedName("calendar")
        val calendar: List<Any>,
        @SerializedName("completeChallengeCount")
        val completeChallengeCount: Int,
        @SerializedName("completeCourseCount")
        val completeCourseCount: Int,
        @SerializedName("email")
        val email: String,
        @SerializedName("feedCount")
        val feedCount: Int,
        @SerializedName("nickname")
        val nickname: String
    )
}