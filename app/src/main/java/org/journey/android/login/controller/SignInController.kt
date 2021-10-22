package org.journey.android.login.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.login.data.RequestEmailSignInDTO
import org.journey.android.login.data.ResponseEmailSignInDTO

interface SignInController {
    fun emailSingIn(requestEmailSignInDTO: RequestEmailSignInDTO) : Single<ResponseEmailSignInDTO>
}