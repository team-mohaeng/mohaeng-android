package org.journey.android.community.dto

data class BottomSheetData(
    val tags :String,
    val second_tags : String,
    val diary : String,
    val user_id : String,
    val user_prefer : Int,
    val has_like: Boolean,
    val main_image: String,
    val postId : Int
)