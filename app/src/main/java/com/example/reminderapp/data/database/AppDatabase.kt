package com.example.reminderapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.reminderapp.data.dao.ReminderDao
import com.example.reminderapp.data.model.Reminder

@Database(
    entities = [Reminder::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): ReminderDao
}