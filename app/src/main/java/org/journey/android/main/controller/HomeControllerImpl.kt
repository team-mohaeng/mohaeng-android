package org.journey.android.main.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.main.controller.HomeController
import org.journey.android.main.dto.ResponseHomeDTO
import org.journey.android.network.RetrofitInterface
import org.journey.android.qualifier.AuthRetrofitService
import javax.inject.Inject

class HomeControllerImpl @Inject constructor(
    @AuthRetrofitService private val retrofitInterface: RetrofitInterface
) : HomeController {
    override fun getHome(client: String): Single<ResponseHomeDTO> =
        retrofitInterface.getHomeResource(client)
}