package org.journey.android.login.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.login.data.RequestEmailSignInDTO
import org.journey.android.login.data.ResponseEmailSignInDTO
import org.journey.android.login.data.ResponseGoogleSignInDTO
import org.journey.android.login.data.ResponseKakaoSignInDTO
import org.journey.android.network.RetrofitInterface
import javax.inject.Inject

class SignInControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : SignInController {
    override fun emailSingIn(fcmToken: String, requestEmailSignInDTO: RequestEmailSignInDTO): Single<ResponseEmailSignInDTO> =
        retrofitInterface.signInEmail(fcmToken, requestEmailSignInDTO)

    override fun kakaoSignIn(idToken : String, fcmToken : String): Single<ResponseKakaoSignInDTO> =
        retrofitInterface.kakaoLogin(idToken,fcmToken)

    override fun googleSignIn(idToken: String, fcmToken: String): Single<ResponseGoogleSignInDTO> =
        retrofitInterface.googleLogin(idToken, fcmToken)

}