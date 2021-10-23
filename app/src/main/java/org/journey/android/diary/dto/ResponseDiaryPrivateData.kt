package org.journey.android.diary.dto

data class ResponseDiaryPrivateData(
    val status: Int,
    val data: Data?
) {
    data class Data(
        val feeds: List<Feedsfaction>?
    ) {
        data class Feedsfaction(
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
    }
}

data class Emojifaction(
    val id: Int,
    val count: Int
)