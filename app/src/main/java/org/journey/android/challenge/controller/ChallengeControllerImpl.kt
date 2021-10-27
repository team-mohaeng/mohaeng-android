package org.journey.android.challenge.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.challenge.data.response.ResponseTodayChallengeDTO
import org.journey.android.challenge.data.response.ResponseValidateChallengeDTO
import org.journey.android.network.RetrofitInterface
import javax.inject.Inject

class ChallengeControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : ChallengeController{
    override fun todayChallenge(client: String): Single<ResponseTodayChallengeDTO> =
        retrofitInterface.checkTodayChallenge(client)

    override fun validateChallenge(
        courseId: String,
        challengeId: String
    ): Single<ResponseValidateChallengeDTO> =
        retrofitInterface.putValidateChallenge(courseId, challengeId)

}