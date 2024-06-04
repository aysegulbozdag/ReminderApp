package com.example.reminderapp.ui.createnewreminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reminderapp.data.model.Reminder
import com.example.reminderapp.data.repositories.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewReminderViewModel @Inject constructor(private val reminderRepository: ReminderRepository) :
    ViewModel() {

        fun saveReminder(reminder: Reminder) {
            // Save reminder

            viewModelScope.launch {
                reminderRepository.addReminder(reminder)
            }
        }

}