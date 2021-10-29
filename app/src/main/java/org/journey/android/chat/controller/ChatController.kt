package org.journey.android.chat.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.pushalarm.data.ResponsePushAlarmDTO

interface ChatController {
    fun pushAlarm() : Single<ResponsePushAlarmDTO>
}