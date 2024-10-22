package com.example.moviedbtestassignment.db.converters;

import androidx.room.TypeConverter

class DbConverter {


    @TypeConverter
    fun toListInt(value: String?): List<Int>? {
        val stringValue = value ?: return null
        return stringValue.split(",").map { it.toInt() }
    }

    @TypeConverter
    fun fromListInt(accessList: List<Int>?) :String?{
        val list = accessList?:return null
        return list.joinToString(",")

    }


}