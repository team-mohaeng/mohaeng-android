package org.journey.android.presentation.entry.findpw.controller.modify

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.entry.findpw.data.request.RequestChangePasswordDTO
import org.journey.android.presentation.entry.findpw.data.response.ResponseChangePasswordDTO
import org.journey.android.data.network.RetrofitInterface
import javax.inject.Inject

class ChangePasswordControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : ChangePasswordController {
    override fun changePassword(requestChangePasswordDTO: RequestChangePasswordDTO): Single<ResponseChangePasswordDTO> =
        retrofitInterface.changePassWord(requestChangePasswordDTO)
}