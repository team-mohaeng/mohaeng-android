package org.journey.android.login.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.login.data.RequestEmailSignInDTO
import org.journey.android.login.data.ResponseEmailSignInDTO
import org.journey.android.network.RetrofitInterface
import javax.inject.Inject

class SignInControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : SignInController {
    override fun emailSingIn(requestEmailSignInDTO: RequestEmailSignInDTO): Single<ResponseEmailSignInDTO> =
        retrofitInterface.signInEmail(requestEmailSignInDTO)
}