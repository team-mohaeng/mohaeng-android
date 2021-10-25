package org.journey.android.signup.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.network.RetrofitInterface
import org.journey.android.signup.data.RequestSignupDTO
import org.journey.android.signup.data.ResponseSignupDTO
import org.journey.android.signup.data.ResponseSocialSignUpDTO
import javax.inject.Inject

class SignUpControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : SignUpController {
    override fun emailSignUp(fcmToken: String, requestSignupDTO: RequestSignupDTO): Single<ResponseSignupDTO> =
        retrofitInterface.signUp(fcmToken, requestSignupDTO)

    override fun kakaoSignUp(idToken: String, fcmToken: String): Single<ResponseSocialSignUpDTO> =
        retrofitInterface.signUpKakao(idToken,fcmToken)


    override fun googleSignUp(idToken: String, fcmToken: String): Single<ResponseSocialSignUpDTO> =
        retrofitInterface.signUpGoogle(idToken,fcmToken)

}