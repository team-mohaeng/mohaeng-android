package org.journey.android.challenge.data.dto.validate


import com.google.gson.annotations.SerializedName

data class ValidateChallengeDataDTO(
    @SerializedName("challengeCompletion")
    val validateChallengeCompletionDTO: ValidateChallengeCompletionDTO,
    @SerializedName("characterImg")
    val characterImg: String,
    @SerializedName("courseCompletion")
    val validateCourseCompletionDTO: ValidateCourseCompletionDTO?,
    @SerializedName("levelUp")
    val validateLevelUpDTO: ValidateLevelUpDTO
)