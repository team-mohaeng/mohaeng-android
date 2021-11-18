package org.journey.android.pushalarm.data.response


import com.google.gson.annotations.SerializedName
import org.journey.android.pushalarm.data.PushAlarmDataDTO

data class ResponsePushAlarmDTO(
    @SerializedName("data")
    val pushAlarmDataDTO: PushAlarmDataDTO,
    @SerializedName("status")
    val status: Int
)