package org.journey.android.course.data.source

import io.reactivex.rxjava3.core.Single
import org.journey.android.course.data.dto.ResponseCourseCatalogDTO
import org.journey.android.network.RetrofitInterface
import javax.inject.Inject

class CourseCatalogDataSourceImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : CourseCatalogDataSource {
    override fun getCourseCatalog(): Single<ResponseCourseCatalogDTO> =
    retrofitInterface.getCourses()
}