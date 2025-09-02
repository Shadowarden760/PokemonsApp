package com.test.pokemonapp.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.pokemonapp.data.network.models.Stats

class ListTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromList(list: List<Pair<Stats, Int>>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toList(data: String): List<Pair<Stats, Int>> {
        val listType = object : TypeToken<List<Pair<Stats, Int>>>() {}.type
        return gson.fromJson(data, listType)
    }
}