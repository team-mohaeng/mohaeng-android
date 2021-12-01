package org.journey.android.course.data.source

import io.reactivex.rxjava3.core.Single
import org.journey.android.course.data.dto.ResponseCourseCatalogDTO

interface CourseCatalogDataSource {
    fun getCourseCatalog() : Single<ResponseCourseCatalogDTO>
}