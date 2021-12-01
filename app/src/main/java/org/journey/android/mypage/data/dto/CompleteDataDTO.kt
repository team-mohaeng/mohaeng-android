package org.journey.android.mypage.data.dto


import com.google.gson.annotations.SerializedName

data class CompleteDataDTO(
    @SerializedName("courses")
    val courses: List<CompleteCourseDTO?>,
    @SerializedName("isProgress")
    val isProgress: Boolean
)