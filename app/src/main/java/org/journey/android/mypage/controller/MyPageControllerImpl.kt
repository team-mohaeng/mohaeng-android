package org.journey.android.mypage.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.mypage.data.ResponseCheckMyPageDTO
import org.journey.android.network.RetrofitInterface
import javax.inject.Inject

class MyPageControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : MyPageController{
    override fun myPageResource(): Single<ResponseCheckMyPageDTO> =
        retrofitInterface.checkMyPage()
}