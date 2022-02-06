package org.journey.android.presentation.entry.fcm.data.dto


import com.google.gson.annotations.SerializedName

data class PushAlarmMessages(
    val date: String,
    val isNew: Boolean,
    val message: List<String>?
)