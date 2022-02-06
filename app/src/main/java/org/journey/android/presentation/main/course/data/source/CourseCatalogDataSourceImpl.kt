package org.journey.android.presentation.main.course.data.source

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.course.data.dto.ResponseCourseCatalogDTO
import org.journey.android.data.network.RetrofitInterface
import javax.inject.Inject

class CourseCatalogDataSourceImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : CourseCatalogDataSource {
    override fun getCourseCatalog(): Single<ResponseCourseCatalogDTO> =
    retrofitInterface.getCourses()
}