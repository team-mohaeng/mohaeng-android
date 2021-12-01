package org.journey.android.signup

import org.journey.android.R

enum class NickNameStatus(val alertMessage : Int) {
    IS_NOT_AVAILABLE_NICKNAME(R.string.not_available_nickname),
    CHECK_NICKNAME_REPETITION(R.string.check_nickname_repetition);
}