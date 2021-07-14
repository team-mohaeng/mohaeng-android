package org.journey.android.findpw.dto

data class ResponsePasswordData(
    val status: Int,
    val data: EmailData?
) {
    data class EmailData(
        val number: Int
    )
}


