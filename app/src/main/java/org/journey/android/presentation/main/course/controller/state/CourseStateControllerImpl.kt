package org.journey.android.presentation.main.course.controller.state

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.course.data.dto.ResponseStartChallengeDTO
import org.journey.android.data.network.RetrofitInterface
import javax.inject.Inject

class CourseStateControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
)  : CourseStateController {
    override fun putCourseState(client: String, courseId : Int): Single<ResponseStartChallengeDTO> =
        retrofitInterface.putCourse(client, courseId)
}