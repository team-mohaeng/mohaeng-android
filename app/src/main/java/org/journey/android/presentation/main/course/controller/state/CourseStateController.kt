package org.journey.android.presentation.main.course.controller.state

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.course.data.dto.ResponseStartChallengeDTO

interface CourseStateController {
    fun putCourseState(client : String, courseId : Int) : Single<ResponseStartChallengeDTO>
}