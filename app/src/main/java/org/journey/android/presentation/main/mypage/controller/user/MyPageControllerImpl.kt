package org.journey.android.presentation.main.mypage.controller.user

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.mypage.data.dto.response.ResponseCheckMyPageDTO
import org.journey.android.data.network.RetrofitInterface
import javax.inject.Inject

class MyPageControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : MyPageController {
    override fun myPageResource(): Single<ResponseCheckMyPageDTO> =
        retrofitInterface.checkMyPage()
}