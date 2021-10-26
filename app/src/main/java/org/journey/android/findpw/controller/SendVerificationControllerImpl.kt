package org.journey.android.findpw.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.findpw.controller.SendVerificationController
import org.journey.android.findpw.data.ResponseVerificationCodeDTO
import org.journey.android.network.RetrofitInterface
import javax.inject.Inject

class SendVerificationControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : SendVerificationController {
    override fun sendVerification(email : String): Single<ResponseVerificationCodeDTO> =
        retrofitInterface.sendVerificationCode(email)
}