package org.journey.android.mypage.data.dto


import com.google.gson.annotations.SerializedName

data class CompleteDataDTO(
    @SerializedName("courses")
    val cours: List<CompleteCourseDTO>,
    @SerializedName("isProgress")
    val isProgress: Boolean
)