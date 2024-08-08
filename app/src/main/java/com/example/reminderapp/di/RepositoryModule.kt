package com.example.reminderapp.di

import android.app.NotificationManager
import com.example.reminderapp.data.dao.ReminderDao
import com.example.reminderapp.data.repositories.NotificationRepository
import com.example.reminderapp.data.repositories.NotificationRepositoryImpl
import com.example.reminderapp.data.repositories.ReminderRepository
import com.example.reminderapp.data.repositories.ReminderRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun bindDataRepository(reminderDao: ReminderDao): ReminderRepository =
        ReminderRepositoryImpl(reminderDao)

    @Provides
    fun provideNotificationRepository(notificationManager: NotificationManager) : NotificationRepository = NotificationRepositoryImpl(notificationManager)

}