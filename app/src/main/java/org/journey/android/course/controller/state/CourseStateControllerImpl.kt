package org.journey.android.course.controller.state

import io.reactivex.rxjava3.core.Single
import org.journey.android.course.controller.state.CourseStateController
import org.journey.android.course.data.dto.ResponseStartChallengeDTO
import org.journey.android.network.RetrofitInterface
import javax.inject.Inject

class CourseStateControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
)  : CourseStateController {
    override fun putCourseState(client: String, courseId : Int): Single<ResponseStartChallengeDTO> =
        retrofitInterface.putCourse(client, courseId)
}