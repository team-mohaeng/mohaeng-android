package org.journey.android.presentation.entry.signup

import org.journey.android.R

enum class PasswordStatus(val alertMessage: Int) {
    NOT_AVAILABLE_PASSWORD(R.string.not_available_password),
    DOES_NOT_MATCHED_PASSWORD_CHECK(R.string.doesnt_matched_password_check),
    IS_AVAILABLE_PASSWORD(R.string.is_available_password);
}