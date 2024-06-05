package com.example.reminderapp.di

import com.example.reminderapp.data.dao.ReminderDao
import com.example.reminderapp.data.repositories.ReminderRepository
import com.example.reminderapp.data.repositories.ReminderRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun bindDataRepository(reminderDao: ReminderDao): ReminderRepository =
        ReminderRepositoryImpl(reminderDao)
}