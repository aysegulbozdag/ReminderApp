package com.example.reminderapp.data.repositories

import android.content.Context

interface NotificationRepository {
    fun sendNotification(context: Context, title : String, message: String)
}