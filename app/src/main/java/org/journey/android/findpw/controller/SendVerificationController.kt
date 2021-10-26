package org.journey.android.findpw.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.findpw.data.ResponseVerificationCodeDTO

interface SendVerificationController {
    fun sendVerification(email : String) : Single<ResponseVerificationCodeDTO>
}