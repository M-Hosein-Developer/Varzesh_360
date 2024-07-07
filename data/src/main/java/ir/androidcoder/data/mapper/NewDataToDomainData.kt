package ir.androidcoder.data.mapper

import ir.androidcoder.data.local.entities.NewsListEntity
import ir.androidcoder.domain.entities.NewsEntity

fun NewsListEntity.toDomainEntity() = NewsEntity(
    code = code,
    created_at = created_at,
    id = id,
    infoAPI = infoAPI ?: "",
    link = link ?: "",
    live_detail = live_detail ?: "",
    post_type = post_type ?: "",
    primary_media = primary_media.toDomainEntity(),
    published_at = published_at,
    slug = slug ?: "",
    sub_title = sub_title ?: "",
    super_title = super_title ?: "",
    title = title ?: ""
)

fun NewsListEntity.PrimaryMedia.toDomainEntity() = NewsEntity.PrimaryMedia(
    cover_image = cover_image ?: "",
    duration = duration ?: 0,
    file = file ?: "",
    hour_duration = hour_duration ?: 0,
    id = id ?: "",
    media_type = media_type ?: "",
    minute_duration = minute_duration ?: 0,
    second_duration = second_duration ?: 0,
    thumbnail = thumbnail ?: "",
    title = title ?: "",
    upload_video_link = upload_video_link ?: ""
)