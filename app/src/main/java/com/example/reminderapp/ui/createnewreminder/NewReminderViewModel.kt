package com.example.reminderapp.ui.createnewreminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import com.example.reminderapp.data.model.Reminder
import com.example.reminderapp.data.repositories.ReminderRepository
import com.example.reminderapp.services.ReminderWorker
import com.example.reminderapp.services.createNotificationChannel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class NewReminderViewModel @Inject constructor(private val reminderRepository: ReminderRepository) :
    ViewModel() {

        fun saveReminder(reminder: Reminder) {
            viewModelScope.launch {
                reminderRepository.addReminder(reminder)
            }
        }

}