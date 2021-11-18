package org.journey.android.signup.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.network.RetrofitInterface
import org.journey.android.signup.data.request.RequestChangeNickNameDTO
import org.journey.android.signup.data.response.ResponseChangeNickNameDTO
import javax.inject.Inject

class ChangeNickNameControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : ChangeNickNameController{
    override fun changeNickName(requestChangeNickNameDTO: RequestChangeNickNameDTO): Single<ResponseChangeNickNameDTO> =
        retrofitInterface.changeNickName(requestChangeNickNameDTO)
}