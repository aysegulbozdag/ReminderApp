package com.example.reminderapp.ui.createnewreminder.bottomsheetdialog

import androidx.lifecycle.ViewModel
import com.example.reminderapp.utility.convertStringToDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DateTimePickerViewModel : ViewModel() {

    private var _date: Date = Date()
    val date get() = _date

    fun onTimeChangedListener(hourOfDay: Int, minute: Int) {

        val calendar = Calendar.getInstance()
        calendar.time = this._date

        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        _date = calendar.time

    }

    fun onDateChangedListener(year: Int, month: Int, dayOfMonth: Int) {
        _date = convertStringToDate(
            year.toString() + "-" + (month + 1).toString()
                    + "-" + dayOfMonth.toString()
        )
    }

}