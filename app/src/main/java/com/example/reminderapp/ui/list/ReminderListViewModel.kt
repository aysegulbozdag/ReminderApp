package com.example.reminderapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reminderapp.data.model.Reminder
import com.example.reminderapp.data.repositories.ReminderRepository
import com.example.reminderapp.ui.list.adapter.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ReminderListViewModel @Inject constructor(val repository: ReminderRepository) : ViewModel() {

    private var _remindersState: MutableStateFlow<List<ListItem>> =
        MutableStateFlow(emptyList())
    val remindersState = _remindersState.asStateFlow()

    init {
        getAllReminders()
    }

    private fun getAllReminders() {
        viewModelScope.launch {
            repository.getAllReminders.collectLatest {
                _remindersState.value = prepareListItems(groupByDate(it))
            }
        }
    }

    private fun groupByDate(items: List<Reminder>): Map<Date, List<Reminder>> {
        return items.groupBy { it.date }
    }

    private fun prepareListItems(groupedItems: Map<Date, List<Reminder>>): List<ListItem> {
        val listItems = mutableListOf<ListItem>()

        for ((date, items) in groupedItems) {
            listItems.add(ListItem.DateHeader(date))
            for (item in items) {
                listItems.add(ListItem.ReminderItem(item))
            }
        }

        return listItems
    }
}