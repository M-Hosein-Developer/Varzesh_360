package ir.androidcoder.data.mapper

import ir.androidcoder.data.local.entities.NewsListEntity
import ir.androidcoder.domain.entities.NewsEntity

fun NewsListEntity.toNewsEntity(primaryMedia: NewsEntity.PrimaryMedia): NewsEntity {
    return NewsEntity(
        code = this.code,
        created_at = this.created_at,
        id = this.id,
        infoAPI = this.infoAPI,
        link = this.link,
        live_detail = this.live_detail ?: "",
        post_type = this.post_type,
        primary_media = primaryMedia,
        published_at = this.published_at,
        slug = this.slug,
        sub_title = this.sub_title ?: "",
        super_title = this.super_title ?: "",
        title = this.title
    )
}