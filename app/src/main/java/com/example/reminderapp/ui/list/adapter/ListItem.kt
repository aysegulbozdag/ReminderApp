package com.example.reminderapp.ui.list.adapter

import com.example.reminderapp.data.model.Reminder
import java.util.Date

sealed class ListItem {
    data class DateHeader(val date: String) : ListItem()
    data class ReminderItem(val reminder: Reminder) : ListItem()
}