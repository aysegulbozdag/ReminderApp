package com.example.reminderapp.data.typeconverter

import androidx.room.TypeConverter
import com.example.reminderapp.data.model.CheckModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CheckModelConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromCheckModelList(value: List<CheckModel>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCheckModelList(value: String?): List<CheckModel>? {
        val listType = object : TypeToken<List<CheckModel>>() {}.type
        return gson.fromJson(value, listType)
    }
}
