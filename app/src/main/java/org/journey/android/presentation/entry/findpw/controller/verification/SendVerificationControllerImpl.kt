package org.journey.android.presentation.entry.findpw.controller.verification

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.entry.findpw.data.response.ResponseVerificationCodeDTO
import org.journey.android.data.network.RetrofitInterface
import javax.inject.Inject

class SendVerificationControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : SendVerificationController {
    override fun sendVerification(email : String): Single<ResponseVerificationCodeDTO> =
        retrofitInterface.sendVerificationCode(email)
}