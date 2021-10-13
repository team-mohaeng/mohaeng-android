package org.journey.android.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    const val SERVER_DATE_FORMAT = "yyyy-MM-dd"
    const val DOT_DATE_FORMAT = "yyyy.MM.dd"
    const val DATE_FORMAT = "dd"
    const val YEAR_FORMAT = "yyyy"
    const val MONTH_FORMAT = "MM"

    fun getCurrentIntegerDate(format: String): Int {
        val dateFormat = SimpleDateFormat(format, Locale.KOREA)
        return dateFormat.format(Date()).toInt()
    }

    fun getCurrentFormattedDate(): String {
        val dateFormat = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.KOREA)
        return dateFormat.format(Date())
    }

    fun String.parseDotDateFormat(): String {
        val serverDateFormat = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.KOREA)
        val dotDateFormat = SimpleDateFormat(DOT_DATE_FORMAT, Locale.KOREA)
        val date = serverDateFormat.parse(this) ?: throw ParseException("date parse error", 0)
        return dotDateFormat.format(date)
    }
}