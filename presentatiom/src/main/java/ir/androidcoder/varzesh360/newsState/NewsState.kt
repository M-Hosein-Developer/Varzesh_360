package ir.androidcoder.varzesh360.newsState

import ir.androidcoder.domain.entities.NewsEntity

sealed class NewsState {

    data object Idle : NewsState()
    data class NewsData(val news: List<NewsEntity>) : NewsState()
    data class NewsError(val error : String?) : NewsState()

}