package ir.androidcoder.varzesh360.newsState

import ir.androidcoder.domain.entities.NewsEntity

sealed class NewsDetailState {

    data object Idle : NewsDetailState()
    data class NewsData(val news: NewsEntity) : NewsDetailState()
    data class NewsError(val error : String?) : NewsDetailState()

}