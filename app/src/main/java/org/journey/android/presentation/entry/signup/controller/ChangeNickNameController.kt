package org.journey.android.presentation.entry.signup.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.entry.signup.data.request.RequestChangeNickNameDTO
import org.journey.android.presentation.entry.signup.data.response.ResponseChangeNickNameDTO

interface ChangeNickNameController {
    fun changeNickName(requestChangeNickNameDTO: RequestChangeNickNameDTO) : Single<ResponseChangeNickNameDTO>
}