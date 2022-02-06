package org.journey.android.presentation.main.mypage.controller.course

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.mypage.data.dto.response.ResponseCompleteCourseDTO
import org.journey.android.data.network.RetrofitInterface
import javax.inject.Inject

class CompleteCourseControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : CompleteCourseController {
    override fun completeCourse(): Single<ResponseCompleteCourseDTO>
    = retrofitInterface.getCompleteCourseList()
}