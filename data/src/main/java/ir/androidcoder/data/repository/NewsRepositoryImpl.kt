package ir.androidcoder.data.repository

import ir.androidcoder.data.mapper.toDomainEntity
import ir.androidcoder.data.remote.ApiService
import ir.androidcoder.domain.entities.NewsEntity
import ir.androidcoder.domain.repository.NewsRepository

class NewsRepositoryImpl(private val apiService: ApiService) : NewsRepository {

    override suspend fun getNews(): List<NewsEntity> = apiService.getNewsList(1 , "14kDIdzjZw8wqCzb9CxOrPtQpgSNmllIDSKqiq").result.map { it.toDomainEntity() }

}