package org.journey.android.pushalarm.data


import com.google.gson.annotations.SerializedName

data class PushAlarmMessagesDTO(
    @SerializedName("date")
    val date: String,
    @SerializedName("isNew")
    val isNew: Boolean,
    @SerializedName("message")
    val message: List<String>?
)