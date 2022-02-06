package org.journey.android.presentation.main.mypage.controller.user

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.mypage.data.dto.response.ResponseCheckMyPageDTO

interface MyPageController {
    fun myPageResource() : Single<ResponseCheckMyPageDTO>
}