package com.example.reminderapp.utility

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun Date.intervalDateFormatted(): String {
    val today = Date()
    val diffInMillies = today.time - this.time
    val diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)
    val diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillies)
    val diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillies)

    return when {
        this.isSameDay(today) -> when {
            diffInMinutes < 0 -> "Bugün"
            else -> "Geçti"
        }
        diffInDays == 0L -> when {
            diffInHours in 1..23 -> "Geçti"
            else -> "Yakında"
        }
        diffInDays in 1..364 -> "Geçti"
        else -> "Yakında"
    }

}

fun Date.isSameDay(otherDay: Date): Boolean {
    val calOtherDay = Calendar.getInstance()
    val calDate = Calendar.getInstance()
    calOtherDay.time = otherDay
    calDate.time = this
    val sameDay = calOtherDay[Calendar.DAY_OF_YEAR] == calDate[Calendar.DAY_OF_YEAR] &&
            calOtherDay[Calendar.YEAR] == calDate[Calendar.YEAR]

    return sameDay

}


fun Date.timeFormat(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this

    return "${convertToString(this, "d MMM")}, ${
        convertToString(
            this, "HH:mm"
        )
    }"
}

fun convertToString(dateStr: Date, format: String = "yyyy-MM-dd"): String {

    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(dateStr)

}

fun convertStringToDate(dateStr: String, format: String = "yyyy-MM-dd"): Date {

    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.parse(dateStr) as Date

}
