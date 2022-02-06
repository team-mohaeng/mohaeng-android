package org.journey.android.presentation.main.course.data.repository

import io.reactivex.rxjava3.core.Single
import org.journey.android.domain.entity.CourseCatalogEntity
import org.journey.android.presentation.main.course.data.source.CourseCatalogDataSource
import javax.inject.Inject

class CourseCatalogRepositoryImpl @Inject constructor(
    private val courseCatalogDataSource: CourseCatalogDataSource
) : CourseCatalogRepository {
    override fun fetchCourseCatalog(): Single<List<CourseCatalogEntity>> =
        courseCatalogDataSource.getCourseCatalog().map {  response->
            response.catalogDataDTO.courses.map {
                it.convertToCourseCatalogEntity()
            }
        }
}