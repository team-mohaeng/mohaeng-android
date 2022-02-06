package org.journey.android.presentation.main.diary.dto

data class PrivateData(
    val postId:	Int,
    val course:	String,
    val challenge:	Int,
    val image:	String,
    val mood:	Int,
    val content:	String,
    val nickname:	String,
    val year:	String,
    val month:	String,
    val date:	String,
    val day:	String,
    val emoji:	List<Emojifaction>,
    val myEmoji:	Int,
    val isReport:	Boolean,
    val isDelete:	Boolean
)