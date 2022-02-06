package org.journey.android.presentation.main.challenge.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.challenge.data.response.ResponseTodayChallengeDTO
import org.journey.android.presentation.main.challenge.data.response.ResponseValidateChallengeDTO

interface ChallengeController {
    fun todayChallenge(client : String):Single<ResponseTodayChallengeDTO>
    fun validateChallenge(client : String, courseId : String, challengeId : String) : Single<ResponseValidateChallengeDTO>
}