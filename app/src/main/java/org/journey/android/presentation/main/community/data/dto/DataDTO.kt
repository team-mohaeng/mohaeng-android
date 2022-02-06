package org.journey.android.presentation.main.community.data.dto


import com.google.gson.annotations.SerializedName

data class DataDTO(
    @SerializedName("happy")
    val happy: Int,
    @SerializedName("isPenalty")
    val isPenalty: Boolean,
    @SerializedName("levelUp")
    val levelUpDTO: LevelUpDTO?,
    @SerializedName("totalHappy")
    val totalHappy: Int,
    @SerializedName("userHappy")
    val userHappy: Int
)