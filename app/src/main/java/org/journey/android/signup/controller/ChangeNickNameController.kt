package org.journey.android.signup.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.signup.data.RequestChangeNickNameDTO
import org.journey.android.signup.data.ResponseChangeNickNameDTO

interface ChangeNickNameController {
    fun changeNickName(requestChangeNickNameDTO: RequestChangeNickNameDTO) : Single<ResponseChangeNickNameDTO>
}