package org.journey.android.signup.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.signup.data.RequestSignupDTO
import org.journey.android.signup.data.RequestSocialSignUpDTO
import org.journey.android.signup.data.ResponseSignupDTO
import org.journey.android.signup.data.ResponseSocialSignUpDTO

interface SignUpController {
    fun emailSignUp(fcmToken: String, requestSignupDTO: RequestSignupDTO) : Single<ResponseSignupDTO>
    fun socialSignUp(accessToken :String, fcmToken : String, snsType : String, requestSocialSignupDTO: RequestSocialSignUpDTO) : Single<ResponseSocialSignUpDTO>
 }