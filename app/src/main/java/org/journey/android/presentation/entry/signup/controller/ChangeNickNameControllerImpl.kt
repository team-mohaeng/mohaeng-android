package org.journey.android.presentation.entry.signup.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.data.network.RetrofitInterface
import org.journey.android.presentation.entry.signup.data.request.RequestChangeNickNameDTO
import org.journey.android.presentation.entry.signup.data.response.ResponseChangeNickNameDTO
import javax.inject.Inject

class ChangeNickNameControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : ChangeNickNameController{
    override fun changeNickName(requestChangeNickNameDTO: RequestChangeNickNameDTO): Single<ResponseChangeNickNameDTO> =
        retrofitInterface.changeNickName(requestChangeNickNameDTO)
}