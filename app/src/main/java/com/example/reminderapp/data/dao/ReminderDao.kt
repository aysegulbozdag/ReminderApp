package com.example.reminderapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.reminderapp.data.model.Reminder

@Dao
interface ReminderDao {
    @Insert
    suspend fun insertReminder(vararg reminder: Reminder)

    @Query("SELECT * FROM reminder_table")
    suspend fun getAll(): List<Reminder>

    @Query("SELECT * FROM reminder_table WHERE reminder_table.id = :id")
    suspend fun getReminder(id: Int):Reminder
}