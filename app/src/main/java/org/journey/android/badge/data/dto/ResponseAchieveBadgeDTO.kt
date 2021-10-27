package org.journey.android.badge.data.dto


import com.google.gson.annotations.SerializedName
import org.journey.android.badge.data.entity.BadgeEntity

data class ResponseAchieveBadgeDTO(
    @SerializedName("data")
    val dataDTO: DataDTO,
    @SerializedName("status")
    val status: Int,
    val message : String?
)