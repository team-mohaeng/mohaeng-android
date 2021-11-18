package org.journey.android.mypage.controller.course

import io.reactivex.rxjava3.core.Single
import org.journey.android.mypage.controller.course.CompleteCourseController
import org.journey.android.mypage.data.dto.response.ResponseCompleteCourseDTO
import org.journey.android.network.RetrofitInterface
import javax.inject.Inject

class CompleteCourseControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : CompleteCourseController {
    override fun completeCourse(): Single<ResponseCompleteCourseDTO>
    = retrofitInterface.getCompleteCourseList()
}