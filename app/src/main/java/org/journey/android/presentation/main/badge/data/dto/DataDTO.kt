package org.journey.android.presentation.main.badge.data.dto


import com.google.gson.annotations.SerializedName

data class DataDTO(
    @SerializedName("badges")
    val achieveBadgeDTOS: List<AchieveBadgeDTO>
)