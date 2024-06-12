package com.example.reminderapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.reminderapp.data.model.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert
    suspend fun insertReminder(vararg reminder: Reminder)

    @Query("SELECT * FROM reminder_table ORDER BY date DESC")
    fun getAll(): Flow<List<Reminder>>

    @Query("SELECT * FROM reminder_table WHERE reminder_table.id = :id")
    fun getReminder(id: Int): Flow<Reminder>
}