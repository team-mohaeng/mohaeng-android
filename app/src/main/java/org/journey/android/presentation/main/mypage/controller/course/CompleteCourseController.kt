package org.journey.android.presentation.main.mypage.controller.course

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.mypage.data.dto.response.ResponseCompleteCourseDTO

interface CompleteCourseController {
   fun completeCourse() : Single<ResponseCompleteCourseDTO>
}