package com.example.reminderapp.di

import android.content.Context
import com.example.reminderapp.ui.permission_handler.PermissionHandler
import com.example.reminderapp.ui.permission_handler.PermissionHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object PermissionModule {

    @Provides
    @ActivityScoped
    fun providePermissionHandler(@ActivityContext activity: Context): PermissionHandler =
        PermissionHandlerImpl(activity)

}