package org.journey.android.pushalarm.data


import com.google.gson.annotations.SerializedName

data class PushAlarmDataDTO(
    @SerializedName("messages")
    val pushAlarmMessagesDTO: PushAlarmMessagesDTO,
    @SerializedName("profileImg")
    val profileImg: String
)