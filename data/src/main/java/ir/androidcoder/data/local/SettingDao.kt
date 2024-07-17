package ir.androidcoder.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.androidcoder.data.local.entities.SettingEntity

interface SettingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThemeSetting(settingEntity: SettingEntity)

    @Query("SELECT * FROM SettingEntity")
    suspend fun getThemeSetting(): SettingEntity

}