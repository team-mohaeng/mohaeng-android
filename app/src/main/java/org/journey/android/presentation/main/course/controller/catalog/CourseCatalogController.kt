package org.journey.android.presentation.main.course.controller.catalog

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.course.data.dto.ResponseCourseCatalogDTO

interface CourseCatalogController {
    fun getCourseCatalogList() : Single<ResponseCourseCatalogDTO>
}