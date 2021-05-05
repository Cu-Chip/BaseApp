package com.apcc.xview.utils

import android.os.Build
import android.text.Html
import android.text.Spanned
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object FormatHelper{
    /**
     *
     * @param format : any type of date , time format
     * @param data :date, date time or time
     * @return : type of millis seconds
     */
    fun stringToDateTime(data: String, format: String = Constant.FORMAT_DATABASE_TIME): Long {
        if (data.isEmpty() || format.isEmpty() || data.length < format.length)
            return 0
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        try {
            return formatter.parse(data)?.time?:0
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }

    fun dateTimeToString(data:Long,format: String = Constant.FORMAT_DATABASE_TIME):String{
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = data
        return formatter.format(calendar.time)
    }

    fun textToHtml(text: String): Spanned?{
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> Html.fromHtml(
                text,
                Html.FROM_HTML_MODE_LEGACY
            )
            else -> Html.fromHtml(text)
        }
    }
}