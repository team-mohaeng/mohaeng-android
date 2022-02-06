package org.journey.android.presentation.main.chat.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.entry.fcm.data.response.ResponsePushAlarm

interface ChatController {
    fun pushAlarm() : Single<ResponsePushAlarm>
}