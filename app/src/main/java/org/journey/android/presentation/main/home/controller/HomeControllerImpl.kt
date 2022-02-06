package org.journey.android.presentation.main.home.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.home.dto.ResponseHomeDTO
import org.journey.android.data.network.RetrofitInterface
import org.journey.android.data.network.qualifier.AuthRetrofitService
import javax.inject.Inject

class HomeControllerImpl @Inject constructor(
    @AuthRetrofitService private val retrofitInterface: RetrofitInterface
) : HomeController {
    override fun getHome(client: String): Single<ResponseHomeDTO> =
        retrofitInterface.getHomeResource(client)
}