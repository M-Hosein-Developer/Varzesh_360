package ir.androidcoder.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsListEntity(
    val code: Int = 0,
    val created_at: Int = 0,
    @PrimaryKey
    val id: String = "",
    val infoAPI: String = "",
    val link: String = "",
    val live_detail: Any? = null,
    val post_type: String = "",
    val published_at: Int = 0,
    val slug: String = "",
    val sub_title: String = "",
    val super_title: Any? = null,
    val title: String = ""
)


