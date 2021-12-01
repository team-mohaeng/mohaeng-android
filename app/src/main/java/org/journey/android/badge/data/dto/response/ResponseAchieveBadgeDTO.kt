package org.journey.android.badge.data.dto.response


import com.google.gson.annotations.SerializedName
import org.journey.android.badge.data.dto.DataDTO

data class ResponseAchieveBadgeDTO(
    @SerializedName("data")
    val dataDTO: DataDTO,
    @SerializedName("status")
    val status: Int,
    val message : String?
)