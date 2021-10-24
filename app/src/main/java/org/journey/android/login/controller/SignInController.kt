package org.journey.android.login.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.login.data.RequestEmailSignInDTO
import org.journey.android.login.data.ResponseEmailSignInDTO
import org.journey.android.login.data.ResponseKakaoSignInDTO

interface SignInController {
    fun emailSingIn(fcmToken: String, requestEmailSignInDTO: RequestEmailSignInDTO) : Single<ResponseEmailSignInDTO>
    fun kakaoSignIn(header: String) : Single<ResponseKakaoSignInDTO>
}