package org.journey.android.badge.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.badge.data.dto.response.ResponseAchieveBadgeDTO
import org.journey.android.network.RetrofitInterface
import javax.inject.Inject

class BadgeControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : BadgeController{
    override fun putBadgeList(): Single<ResponseAchieveBadgeDTO> =
        retrofitInterface.putAchieveBadgeList()
}