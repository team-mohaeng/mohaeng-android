package org.journey.android.presentation.main.course.controller.catalog

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.course.data.dto.ResponseCourseCatalogDTO
import org.journey.android.data.network.RetrofitInterface
import javax.inject.Inject

class CourseCatalogControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
): CourseCatalogController {
    override fun getCourseCatalogList(): Single<ResponseCourseCatalogDTO> =
        retrofitInterface.getCourses()
}