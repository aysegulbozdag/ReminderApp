package com.example.reminderapp.data.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "reminder_table")
data class Reminder (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var checkList: List<CheckModel>? = null,
    var photoList: List<Uri>? = null,
    var date: Date,
    var active: String = "")

/**
 * Maybe than i can add the fields
 * var repeat: String = "",
 *     var repeatNo: Int = 0,
 *     var repeatType: String = "",
 */