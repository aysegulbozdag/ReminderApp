package com.example.reminderapp.ui.permission_handler

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import javax.inject.Inject

class PermissionHandlerImpl @Inject constructor(private val context: Context) : PermissionHandler {

    companion object {
        const val PERMISSION_REQUEST_NOTIFICATIONS = 1
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun requestPermissionHandler() {
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                PERMISSION_REQUEST_NOTIFICATIONS
            )
        }
    }
}