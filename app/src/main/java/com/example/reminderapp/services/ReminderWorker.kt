package com.example.reminderapp.services

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.reminderapp.R
import com.example.reminderapp.data.repositories.NotificationRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


@HiltWorker
class ReminderWorker @AssistedInject constructor(
    @Assisted val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: NotificationRepository
) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val title = inputData.getString("title") ?: applicationContext.getString(R.string.reminder)
        val message = inputData.getString("message") ?: appContext.getString(R.string.have_task)

        repository.sendNotification(appContext, title, message)

        return Result.success()
    }
}