package org.journey.android.presentation.entry.login.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.entry.login.data.request.RequestEmailSignInDTO
import org.journey.android.presentation.entry.login.data.response.email.ResponseEmailSignInDTO
import org.journey.android.presentation.entry.login.data.response.social.ResponseGoogleSignInDTO
import org.journey.android.presentation.entry.login.data.response.social.ResponseKakaoSignInDTO

interface SignInController {
    fun emailSingIn(fcmToken: String, requestEmailSignInDTO: RequestEmailSignInDTO) : Single<ResponseEmailSignInDTO>
    fun kakaoSignIn(idToken : String, fcmToken : String ) : Single<ResponseKakaoSignInDTO>
    fun googleSignIn(idToken : String, fcmToken : String) : Single<ResponseGoogleSignInDTO>
}