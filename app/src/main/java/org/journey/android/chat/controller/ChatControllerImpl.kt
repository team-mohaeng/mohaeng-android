package org.journey.android.chat.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.network.RetrofitInterface
import org.journey.android.pushalarm.data.ResponsePushAlarmDTO
import javax.inject.Inject

class ChatControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : ChatController{
    override fun pushAlarm(): Single<ResponsePushAlarmDTO> =
        retrofitInterface.getPushAlarmMessage()
}