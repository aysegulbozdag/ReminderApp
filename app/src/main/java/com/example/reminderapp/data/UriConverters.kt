package com.example.reminderapp.data

import android.net.Uri
import androidx.room.TypeConverter
import com.example.reminderapp.data.model.CheckModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UriConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromCheckModelList(value: List<Uri>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCheckModelList(value: String?): List<Uri>? {
        val listType = object : TypeToken<List<Uri>>() {}.type
        return gson.fromJson(value, listType)
    }
}
