package com.example.reminderapp.ui.createnewreminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reminderapp.data.model.Reminder
import com.example.reminderapp.data.repositories.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NewReminderViewModel @Inject constructor(private val reminderRepository: ReminderRepository) :
    ViewModel() {

    private var selectedDate: Date? = null
    private var title = ""

     var btnIsEnabled: StateFlow<Boolean> =
        MutableStateFlow(selectedDate != null && title.isNotEmpty()).asStateFlow()

        fun saveReminder(reminder: Reminder) {
            viewModelScope.launch {
                reminderRepository.addReminder(reminder)
            }
        }

    fun setSelectedDate(date: Date) {
        selectedDate = date
    }

    fun setTitle(title: String) {
        this.title = title
    }

}