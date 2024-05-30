package com.example.reminderapp.data.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.reminderapp.data.model.CheckModel

@Entity(tableName = "reminder_table")
data class Reminder (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var checkList: List<CheckModel>,
    var photoList: List<Uri>,
    var date: String = "",
    var time: String = "",
    var repeat: String = "",
    var repeatNo: Int = 0,
    var repeatType: String = "",
    var active: String = "")