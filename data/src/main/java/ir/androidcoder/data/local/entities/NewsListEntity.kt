package ir.androidcoder.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsListEntity(
    val code: Int,
    val created_at: Int,
    @PrimaryKey
    val id: String,
    val infoAPI: String,
    val link: String,
    val live_detail: Any,
    val post_type: String,
    val published_at: Int,
    val slug: String,
    val sub_title: String,
    val super_title: Any,
    val title: String
)


