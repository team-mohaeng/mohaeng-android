package org.journey.android.course.data.repository

import io.reactivex.rxjava3.core.Single
import org.journey.android.course.data.entity.CourseCatalogEntity
import org.journey.android.course.data.source.CourseCatalogDataSource
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