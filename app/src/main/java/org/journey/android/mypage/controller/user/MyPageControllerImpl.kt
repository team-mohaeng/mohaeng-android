package org.journey.android.mypage.controller.user

import io.reactivex.rxjava3.core.Single
import org.journey.android.mypage.controller.user.MyPageController
import org.journey.android.mypage.data.dto.response.ResponseCheckMyPageDTO
import org.journey.android.network.RetrofitInterface
import javax.inject.Inject

class MyPageControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : MyPageController {
    override fun myPageResource(): Single<ResponseCheckMyPageDTO> =
        retrofitInterface.checkMyPage()
}