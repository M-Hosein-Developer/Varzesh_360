package ir.androidcoder.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.androidcoder.data.local.converter.NewsConverter
import ir.androidcoder.data.local.entities.NewsListEntity

@Database(entities = [NewsListEntity::class], version = 1 , exportSchema = false)
@TypeConverters(NewsConverter::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

}