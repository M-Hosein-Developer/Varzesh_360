package ir.androidcoder.domain.usecase

import ir.androidcoder.domain.entities.NewsEntity

interface NewsUseCase {

    suspend fun getNews(pageNumber: Int) : List<NewsEntity>

}