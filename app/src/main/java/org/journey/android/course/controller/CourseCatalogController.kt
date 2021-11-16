package org.journey.android.course.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.course.data.dto.ResponseCourseCatalogDTO

interface CourseCatalogController {
    fun getCourseCatalogList() : Single<ResponseCourseCatalogDTO>
}