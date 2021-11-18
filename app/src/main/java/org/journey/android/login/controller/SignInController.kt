package org.journey.android.login.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.login.data.request.RequestEmailSignInDTO
import org.journey.android.login.data.response.ResponseEmailSignInDTO
import org.journey.android.login.data.response.ResponseGoogleSignInDTO
import org.journey.android.login.data.response.ResponseKakaoSignInDTO

interface SignInController {
    fun emailSingIn(fcmToken: String, requestEmailSignInDTO: RequestEmailSignInDTO) : Single<ResponseEmailSignInDTO>
    fun kakaoSignIn(idToken : String, fcmToken : String ) : Single<ResponseKakaoSignInDTO>
    fun googleSignIn(idToken : String, fcmToken : String) : Single<ResponseGoogleSignInDTO>
}