package org.journey.android.presentation.main.mypage.data.dto.response


import com.google.gson.annotations.SerializedName
import org.journey.android.presentation.main.mypage.data.dto.CompleteDataDTO

data class ResponseCompleteCourseDTO(
    @SerializedName("data")
    val completeDataDTO: CompleteDataDTO,
    @SerializedName("status")
    val status: Int,
    val message : String?
)