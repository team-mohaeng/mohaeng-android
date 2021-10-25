package org.journey.android.signup.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.signup.data.RequestSignupDTO
import org.journey.android.signup.data.ResponseSignupDTO
import org.journey.android.signup.data.ResponseSocialSignUpDTO

interface SignUpController {
    fun emailSignUp(fcmToken: String, requestSignupDTO: RequestSignupDTO) : Single<ResponseSignupDTO>
    fun kakaoSignUp(idToken : String, fcmToken : String): Single<ResponseSocialSignUpDTO>
    fun googleSignUp(idToken : String, fcmToken : String): Single<ResponseSocialSignUpDTO>
 }