package com.example.reminderapp.data.repositories

import com.example.reminderapp.data.dao.ReminderDao
import com.example.reminderapp.data.model.Reminder
import com.example.reminderapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReminderRepositoryImpl @Inject constructor(
    private val reminderDao: ReminderDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ReminderRepository {
    override val getAllReminders: Flow<List<Reminder>>
        get() = reminderDao.getAll()


    override suspend fun addReminder(reminder: Reminder) = withContext(ioDispatcher) {
        reminderDao.insertReminder(reminder)
    }

    override suspend fun getReminder(id: Int): Flow<Reminder> = withContext(ioDispatcher) {
        reminderDao.getReminder(id)
    }
}