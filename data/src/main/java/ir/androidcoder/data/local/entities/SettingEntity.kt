package ir.androidcoder.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SettingEntity(

    @PrimaryKey
    val dynamicTheme : Boolean,
    val darkTheme : Boolean,

)
