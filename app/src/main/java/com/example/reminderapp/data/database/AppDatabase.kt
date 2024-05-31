package com.example.reminderapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.reminderapp.data.CheckModelConverters
import com.example.reminderapp.data.UriConverters
import com.example.reminderapp.data.dao.ReminderDao
import com.example.reminderapp.data.model.Reminder

@Database(
    entities = [Reminder::class], version = 1
)
@TypeConverters(CheckModelConverters::class, UriConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): ReminderDao
}