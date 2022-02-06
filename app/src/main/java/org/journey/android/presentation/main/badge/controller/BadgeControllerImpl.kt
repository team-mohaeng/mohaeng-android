package org.journey.android.presentation.main.badge.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.badge.data.dto.response.ResponseAchieveBadgeDTO
import org.journey.android.data.network.RetrofitInterface
import javax.inject.Inject

class BadgeControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : BadgeController{
    override fun putBadgeList(id : Int): Single<ResponseAchieveBadgeDTO> =
        retrofitInterface.putAchieveBadgeList()
}