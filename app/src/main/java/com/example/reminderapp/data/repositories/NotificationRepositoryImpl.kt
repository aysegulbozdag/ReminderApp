package com.example.reminderapp.data.repositories

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.reminderapp.R
import com.example.reminderapp.ui.permission_handler.PermissionHandlerImpl.Companion.PERMISSION_REQUEST_NOTIFICATIONS
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(private val notificationManager: NotificationManager) :
    NotificationRepository {

    override fun sendNotification(context: Context, title: String, message: String) {
        val builder = NotificationCompat.Builder(context, "WORK_MANAGER_CHANNEL").apply {
            setSmallIcon(R.drawable.reminder)
            setContentTitle(title)
            setContentText(message)
            setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }

        notificationManager.notify(PERMISSION_REQUEST_NOTIFICATIONS, builder.build())
    }

}