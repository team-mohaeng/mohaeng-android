package org.journey.android.signup.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.network.RetrofitInterface
import org.journey.android.signup.data.RequestSignupDTO
import org.journey.android.signup.data.RequestSocialSignUpDTO
import org.journey.android.signup.data.ResponseSignupDTO
import org.journey.android.signup.data.ResponseSocialSignUpDTO
import javax.inject.Inject

class SignUpControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : SignUpController {
    override fun emailSignUp(fcmToken: String, requestSignupDTO: RequestSignupDTO): Single<ResponseSignupDTO> =
        retrofitInterface.signUp(fcmToken, requestSignupDTO)

    override fun socialSignUp(
        accessToken: String,
        fcmToken: String,
        snsType: String,
        requestSocialSignUpDTO: RequestSocialSignUpDTO
    ): Single<ResponseSocialSignUpDTO> =
        retrofitInterface.snsSignUp(accessToken, fcmToken, snsType,requestSocialSignUpDTO)

}