package org.journey.android.presentation.main.course.data.source

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.course.data.dto.ResponseCourseCatalogDTO

interface CourseCatalogDataSource {
    fun getCourseCatalog() : Single<ResponseCourseCatalogDTO>
}