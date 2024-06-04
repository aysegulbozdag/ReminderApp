package com.example.reminderapp.data.repositories

import com.example.reminderapp.data.model.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {

    val getAllReminders: Flow<List<Reminder>>
    suspend fun addReminder(reminder: Reminder)
    suspend fun getReminder(id: Int): Flow<Reminder>


}