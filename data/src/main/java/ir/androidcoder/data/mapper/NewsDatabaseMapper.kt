package ir.androidcoder.data.mapper

import ir.androidcoder.data.local.entities.NewsListEntity
import ir.androidcoder.data.model.NewsListResponse

fun mapResponseToEntity(response: NewsListResponse): List<NewsListEntity> {
    return response.result.map { result ->
        NewsListEntity(
            code = result.code,
            created_at = result.created_at,
            id = result.id,
            infoAPI = result.infoAPI,
            link = result.link,
            live_detail = result.live_detail,
            post_type = result.post_type,
            primary_media = mapPrimaryMedia(result.primary_media),
            published_at = result.published_at,
            slug = result.slug,
            sub_title = result.sub_title,
            super_title = result.super_title,
            title = result.title
        )
    }
}

private fun mapPrimaryMedia(primaryMedia: NewsListResponse.Result.PrimaryMedia): NewsListEntity.PrimaryMedia {
    return NewsListEntity.PrimaryMedia(
        cover_image = primaryMedia.cover_image,
        duration = primaryMedia.duration,
        file = primaryMedia.`file`,
        hour_duration = primaryMedia.hour_duration,
        id = primaryMedia.id,
        media_type = primaryMedia.media_type,
        minute_duration = primaryMedia.minute_duration,
        second_duration = primaryMedia.second_duration,
        thumbnail = primaryMedia.thumbnail,
        title = primaryMedia.title,
        upload_video_link = primaryMedia.upload_video_link
    )
}
