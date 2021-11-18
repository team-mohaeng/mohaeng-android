package org.journey.android.mypage.controller.course

import io.reactivex.rxjava3.core.Single
import org.journey.android.mypage.data.dto.response.ResponseCompleteCourseDTO

interface CompleteCourseController {
   fun completeCourse() : Single<ResponseCompleteCourseDTO>
}