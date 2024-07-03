package ir.androidcoder.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.androidcoder.data.local.entities.NewsListEntity


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsData(data: List<NewsListEntity>)


    @Query("SELECT * FROM NewsListEntity WHERE id = :id")
    suspend fun selectDetailNewsData(id : String) : NewsListEntity

}