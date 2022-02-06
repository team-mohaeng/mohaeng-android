package org.journey.android.presentation.entry.fcm.data.dto


import com.google.gson.annotations.SerializedName

data class PushAlarmData(
    @SerializedName("messages")
    val pushAlarmMessages: PushAlarmMessages,
    val profileImg: String
)