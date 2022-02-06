package org.journey.android.presentation.entry.login.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.entry.login.data.request.RequestEmailSignInDTO
import org.journey.android.presentation.entry.login.data.response.email.ResponseEmailSignInDTO
import org.journey.android.presentation.entry.login.data.response.social.ResponseGoogleSignInDTO
import org.journey.android.presentation.entry.login.data.response.social.ResponseKakaoSignInDTO
import org.journey.android.data.network.RetrofitInterface
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