package org.journey.android.badge.data.dto


import com.google.gson.annotations.SerializedName
import org.journey.android.badge.data.dto.AchieveBadgeDTO

data class DataDTO(
    @SerializedName("badges")
    val achieveBadgeDTOS: List<AchieveBadgeDTO>
)