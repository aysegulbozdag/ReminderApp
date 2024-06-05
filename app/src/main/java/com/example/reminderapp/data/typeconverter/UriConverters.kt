package com.example.reminderapp.data.typeconverter

import android.net.Uri
import androidx.room.TypeConverter
import com.example.reminderapp.data.model.CheckModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UriConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromUriList(uriList: List<Uri>?): String? {
        if (uriList == null) return null
        return gson.toJson(uriList.map { it.toString() })
    }

    @TypeConverter
    fun toUriList(uriListString: String?): List<Uri>? {
        if (uriListString == null) return null
        val uriStringList: List<String> = gson.fromJson(uriListString, object : TypeToken<List<String>>() {}.type)
        return uriStringList.map { Uri.parse(it) }
    }
}
