package com.example.reminderapp.di

import com.example.reminderapp.data.repositories.ReminderRepository
import com.example.reminderapp.data.repositories.ReminderRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindDataRepository(realEstateRepository: ReminderRepositoryImpl): ReminderRepository
}