package com.efisherylite.app.domain.room.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
class RoomTypeConverters {

    @TypeConverter
    fun toStringArrayList(value: String): ArrayList<String> {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringArrayList(value: ArrayList<String>): String {
        return Gson().toJson(value)
    }

}