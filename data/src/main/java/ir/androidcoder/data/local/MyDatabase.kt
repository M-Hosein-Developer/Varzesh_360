package ir.androidcoder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.androidcoder.data.local.converter.Converter
import ir.androidcoder.data.local.entities.NewsListEntity
import ir.androidcoder.data.local.entities.SettingEntity

@Database(entities = [NewsListEntity::class , SettingEntity::class], version = 1 , exportSchema = false)
@TypeConverters(Converter::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
    abstract fun settingDao(): SettingDao

}