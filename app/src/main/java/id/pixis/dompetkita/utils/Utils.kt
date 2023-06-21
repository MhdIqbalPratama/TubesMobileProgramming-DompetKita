package id.pixis.dompetkita.utils

import android.annotation.SuppressLint
import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getFirstDate() : String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return Converter.dateFormat(calendar.time, "yyyyMMdd")
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate() : String{
        val sdf = SimpleDateFormat("yyyyMMdd")
        return sdf.format(Date())
    }

    fun getLastWeek(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        return Converter.dateFormat(calendar.time, "yyyyMMdd")
    }

    fun getLastDate() : String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        return Converter.dateFormat(calendar.time, "yyyyMMdd")
    }

    fun getDrawableIdFromFileName(context: Context, nameOfDrawable: String): Int {
        return context.resources.getIdentifier(nameOfDrawable, "drawable", context.packageName)
    }
}