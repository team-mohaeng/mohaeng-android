package org.journey.android.course.controller.state

import io.reactivex.rxjava3.core.Single
import org.journey.android.course.data.dto.ResponseStartChallengeDTO

interface CourseStateController {
    fun putCourseState(client : String, courseId : Int) : Single<ResponseStartChallengeDTO>
}