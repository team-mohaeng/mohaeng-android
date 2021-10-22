package org.journey.android.signup.data.dto.response

import com.google.gson.annotations.SerializedName
import org.journey.android.signup.data.dto.DataDTO

data class ResponseSignupDTO(
    @SerializedName("data")
    val dataDTO: DataDTO,
    @SerializedName("status")
    val status: Int
)