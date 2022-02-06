package org.journey.android.presentation.entry.findpw.controller.modify

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.entry.findpw.data.request.RequestChangePasswordDTO
import org.journey.android.presentation.entry.findpw.data.response.ResponseChangePasswordDTO

interface ChangePasswordController {
    fun changePassword(requestChangePasswordDTO: RequestChangePasswordDTO) : Single<ResponseChangePasswordDTO>
}