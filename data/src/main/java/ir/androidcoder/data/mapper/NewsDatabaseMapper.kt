package ir.androidcoder.data.mapper

import ir.androidcoder.data.local.entities.NewsListEntity
import ir.androidcoder.data.model.NewsListResponse

fun fromResponse(response: NewsListResponse): List<NewsListEntity> {
    return response.result.map { result ->
        NewsListEntity(
            code = result.code,
            created_at = result.created_at,
            id = result.id,
            infoAPI = result.infoAPI,
            link = result.link,
//            live_detail = result.live_detail,
            post_type = result.post_type,
            published_at = result.published_at,
            slug = result.slug,
            sub_title = result.sub_title ?: "",
//            super_title = result.super_title,
            title = result.title
        )
    }
}
