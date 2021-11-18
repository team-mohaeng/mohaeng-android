package org.journey.android.mypage.controller.user

import io.reactivex.rxjava3.core.Single
import org.journey.android.mypage.data.dto.response.ResponseCheckMyPageDTO

interface MyPageController {
    fun myPageResource() : Single<ResponseCheckMyPageDTO>
}