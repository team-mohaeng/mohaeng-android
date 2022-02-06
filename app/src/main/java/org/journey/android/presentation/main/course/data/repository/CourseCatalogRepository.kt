package org.journey.android.presentation.main.course.data.repository

import io.reactivex.rxjava3.core.Single
import org.journey.android.domain.entity.CourseCatalogEntity

interface CourseCatalogRepository {
    fun fetchCourseCatalog() : Single<List<CourseCatalogEntity>>
}