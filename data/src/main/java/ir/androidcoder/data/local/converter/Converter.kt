package ir.androidcoder.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.androidcoder.data.local.entities.NewsListEntity

class Converter {

    @TypeConverter
    fun fromPrimaryMedia(primaryMedia: NewsListEntity.PrimaryMedia): String {
        return Gson().toJson(primaryMedia)
    }

    @TypeConverter
    fun toPrimaryMedia(data: String): NewsListEntity.PrimaryMedia {
        val type = object : TypeToken<NewsListEntity.PrimaryMedia>() {}.type
        return Gson().fromJson(data, type)
    }

}