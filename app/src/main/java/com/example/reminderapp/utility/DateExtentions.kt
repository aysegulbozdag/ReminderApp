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

    return when {
        diffInDays == 0L -> when {
            diffInHours < 24 -> "Geçti"
            else -> "Bugün"
        }

        diffInDays in 1..364 -> "Geçti"
        else -> "Yakında"
    }

}


fun Date.format(): String {
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
