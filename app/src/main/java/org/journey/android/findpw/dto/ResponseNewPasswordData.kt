package org.journey.android.findpw.dto

data class ResponseNewPasswordData(
    val status : Int,
    val data : NewPassword?
){
    data class NewPassword(
        val jwt : String
    )
}
