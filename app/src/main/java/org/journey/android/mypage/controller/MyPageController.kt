package org.journey.android.mypage.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.mypage.data.ResponseCheckMyPageDTO

interface MyPageController {
    fun myPageResource() : Single<ResponseCheckMyPageDTO>
}