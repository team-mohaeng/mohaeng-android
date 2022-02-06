package org.journey.android.presentation.entry.findpw.controller.verification

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.entry.findpw.data.response.ResponseVerificationCodeDTO

interface SendVerificationController {
    fun sendVerification(email : String) : Single<ResponseVerificationCodeDTO>
}