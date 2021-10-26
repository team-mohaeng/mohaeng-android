package org.journey.android.findpw.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.findpw.data.RequestChangePasswordDTO
import org.journey.android.findpw.data.ResponseChangePasswordDTO

interface ChangePasswordController {
    fun changePassword(requestChangePasswordDTO: RequestChangePasswordDTO) : Single<ResponseChangePasswordDTO>
}