package org.journey.android.presentation.entry.signup.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.entry.signup.data.request.RequestSignupDTO
import org.journey.android.presentation.entry.signup.data.request.RequestSocialSignUpDTO
import org.journey.android.presentation.entry.signup.data.response.ResponseSignupDTO
import org.journey.android.presentation.entry.signup.data.response.ResponseSocialSignUpDTO

interface SignUpController {
    fun emailSignUp(fcmToken: String, requestSignupDTO: RequestSignupDTO) : Single<ResponseSignupDTO>
    fun socialSignUp(accessToken :String, fcmToken : String, snsType : String, requestSocialSignupDTO: RequestSocialSignUpDTO) : Single<ResponseSocialSignUpDTO>
 }