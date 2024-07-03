package ir.androidcoder.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson


class NewsConverter {

    @TypeConverter
    fun fromString(value: String?): Any? {
        return Gson().fromJson(value, Any::class.java)
    }

    @TypeConverter
    fun fromAny(any: Any?): String? {
        return Gson().toJson(any)
    }
}