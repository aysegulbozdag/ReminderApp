package com.example.reminderapp.di

import com.example.reminderapp.data.dao.ReminderDao
import com.example.reminderapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    fun provideDao(appDatabase: AppDatabase): ReminderDao {
        return appDatabase.userDao()
    }
}