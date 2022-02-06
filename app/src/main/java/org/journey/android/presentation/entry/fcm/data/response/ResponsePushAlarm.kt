package org.journey.android.presentation.entry.fcm.data.response


import com.google.gson.annotations.SerializedName
import org.journey.android.presentation.entry.fcm.data.dto.PushAlarmData

data class ResponsePushAlarm(
    @SerializedName("data")
    val pushAlarmData: PushAlarmData,
    @SerializedName("status")
    val status: Int
)