package ir.androidcoder.domain.repository

import ir.androidcoder.domain.entities.NewsEntity

interface NewsRepository {

    suspend fun getNews(pageNumber : Int) : List<NewsEntity>

}