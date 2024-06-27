package com.example.reminderapp.services

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.reminderapp.R
import com.example.reminderapp.utility.sendNotification

class ReminderWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val title = inputData.getString("title") ?: applicationContext.getString( R.string.reminder)
        val message = inputData.getString("message") ?: applicationContext.getString(R.string.have_task)

        sendNotification(applicationContext, title, message)

        return Result.success()
    }
}