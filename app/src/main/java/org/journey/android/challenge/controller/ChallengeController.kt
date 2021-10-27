package org.journey.android.challenge.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.challenge.data.response.ResponseTodayChallengeDTO
import org.journey.android.challenge.data.response.ResponseValidateChallengeDTO

interface ChallengeController {
    fun todayChallenge(client : String):Single<ResponseTodayChallengeDTO>
    fun validateChallenge(courseId : String, challengeId : String) : Single<ResponseValidateChallengeDTO>
}