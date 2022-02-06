package org.journey.android.presentation.main.chat.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.data.network.RetrofitInterface
import org.journey.android.presentation.entry.fcm.data.response.ResponsePushAlarm
import javax.inject.Inject

class ChatControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : ChatController{
    override fun pushAlarm(): Single<ResponsePushAlarm> =
        retrofitInterface.getPushAlarmMessage()
}