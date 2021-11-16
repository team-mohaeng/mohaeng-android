package org.journey.android.course.data.repository

import io.reactivex.rxjava3.core.Single
import org.journey.android.course.data.entity.CourseCatalogEntity

interface CourseCatalogRepository {
    fun fetchCourseCatalog() : Single<List<CourseCatalogEntity>>
}