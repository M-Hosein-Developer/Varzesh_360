package ir.androidcoder.data.repository

import android.util.Log
import ir.androidcoder.data.local.NewsDao
import ir.androidcoder.data.mapper.mapResponseToEntity
import ir.androidcoder.data.mapper.toDomainEntity
import ir.androidcoder.data.remote.ApiService
import ir.androidcoder.domain.entities.NewsEntity
import ir.androidcoder.domain.repository.NewsRepository

class NewsRepositoryImpl(private val apiService: ApiService, private val dao: NewsDao) : NewsRepository {

    override suspend fun getNews(pageNumber: Int): List<NewsEntity> {

        val data = apiService.getNewsList(pageNumber, "14kDIdzjZw8wqCzb9CxOrPtQpgSNmllIDSKqiq")

        try {
            dao.insertNewsData(mapResponseToEntity(data))
            Log.v("getNews", "Data inserted into database")
        } catch (e: Exception) {
            Log.e("getNews", "Error inserting data into database: ${e.message}", e)
        }

        return data.result.map { it.toDomainEntity() }
    }


    override suspend fun getNewsDetailFromDb(id: String): NewsEntity = dao
        .selectDetailNewsData(id).toDomainEntity()

}