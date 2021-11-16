package org.journey.android.course.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.course.data.dto.ResponseCourseCatalogDTO
import org.journey.android.network.RetrofitInterface
import javax.inject.Inject

class CourseCatalogControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
): CourseCatalogController {
    override fun getCourseCatalogList(): Single<ResponseCourseCatalogDTO> =
        retrofitInterface.getCourses()
}