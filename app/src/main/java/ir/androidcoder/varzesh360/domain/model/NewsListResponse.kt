package ir.androidcoder.varzesh360.domain.model

data class NewsListResponse(
    val `by`: String,
    val channel: String,
    val documents: String,
    val github: String,
    val npm: String,
    val result: List<Result>,
    val website: String
) {
    data class Result(
        val code: Int,
        val created_at: Int,
        val id: String,
        val infoAPI: String,
        val link: String,
        val live_detail: Any,
        val post_type: String,
        val primary_media: PrimaryMedia,
        val published_at: Int,
        val slug: String,
        val sub_title: String,
        val super_title: Any,
        val title: String
    ) {
        data class PrimaryMedia(
            val cover_image: String,
            val duration: Int,
            val `file`: String,
            val hour_duration: Int,
            val id: String,
            val media_type: String,
            val minute_duration: Int,
            val second_duration: Int,
            val thumbnail: String,
            val title: String,
            val upload_video_link: String
        )
    }
}