package ir.androidcoder.domain.usecase

import ir.androidcoder.domain.entities.NewsEntity

interface NewsUseCase {

    suspend fun getNews() : List<NewsEntity>

}