package org.journey.android.community.data.dto.response


import com.google.gson.annotations.SerializedName
import org.journey.android.community.data.dto.DataDTO

data class ResponsePostCommunityDTO(
    @SerializedName("data")
    val dataDTO: DataDTO,
    val status: Int,
    val message: String
)