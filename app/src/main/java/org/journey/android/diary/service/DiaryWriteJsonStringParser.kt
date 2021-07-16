package org.journey.android.diary.service

import com.google.gson.Gson

object DiaryWriteJsonStringParser {
    fun jsonParseToString(T:Any):String{
        val gson = Gson()
        return gson.toJson(T)
    }
}