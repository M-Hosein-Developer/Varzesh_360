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
    val live_detail: String? = "",
    val post_type: String = "",
    val primary_media: PrimaryMedia = PrimaryMedia(),
    val published_at: Int = 0,
    val slug: String = "",
    val sub_title: String? = "",
    val super_title: String? = "",
    val title: String = ""
){
    data class PrimaryMedia(
        val cover_image: String? = "",
        val duration: Int? = 0,
        val `file`: String? = "",
        val hour_duration: Int? = 0,
        val id: String? = "",
        val media_type: String? = "",
        val minute_duration: Int? = 0,
        val second_duration: Int? = 0,
        val thumbnail: String? = "",
        val title: String? = "",
        val upload_video_link: String? = ""
    )
}

