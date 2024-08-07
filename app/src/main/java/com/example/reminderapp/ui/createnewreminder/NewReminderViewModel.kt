package com.example.reminderapp.ui.createnewreminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reminderapp.data.model.Reminder
import com.example.reminderapp.data.repositories.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NewReminderViewModel @Inject constructor(private val reminderRepository: ReminderRepository) :
    ViewModel() {

    private val _selectedDate = MutableStateFlow<Date?>(null)
    private val selectedDate: StateFlow<Date?> get() = _selectedDate

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> get() = _title

    val btnIsEnabled = selectedDate.combine(title) { date, tittle ->
        date != null && tittle.isNotEmpty()
    }


    fun saveReminder(reminder: Reminder) {
        viewModelScope.launch {
            reminderRepository.addReminder(reminder)
        }
    }

    fun setSelectedDate(date: Date) {
        _selectedDate.value = date
    }

    fun setTitle(title: String) {
        _title.value = title
    }

}