package org.journey.android.presentation.main.challenge.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.challenge.data.response.ResponseTodayChallengeDTO
import org.journey.android.presentation.main.challenge.data.response.ResponseValidateChallengeDTO
import org.journey.android.data.network.RetrofitInterface
import javax.inject.Inject

class ChallengeControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : ChallengeController{
    override fun todayChallenge(client: String): Single<ResponseTodayChallengeDTO> =
        retrofitInterface.checkTodayChallenge(client)

    override fun validateChallenge(
        client : String,
        courseId: String,
        challengeId: String
    ): Single<ResponseValidateChallengeDTO> =
        retrofitInterface.putValidateChallenge(client,courseId, challengeId)

}