package org.journey.android.findpw.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.findpw.data.RequestChangePasswordDTO
import org.journey.android.findpw.data.ResponseChangePasswordDTO
import org.journey.android.network.RetrofitInterface
import javax.inject.Inject

class ChangePasswordControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : ChangePasswordController {
    override fun changePassword(requestChangePasswordDTO: RequestChangePasswordDTO): Single<ResponseChangePasswordDTO> =
        retrofitInterface.changePassWord(requestChangePasswordDTO)
}