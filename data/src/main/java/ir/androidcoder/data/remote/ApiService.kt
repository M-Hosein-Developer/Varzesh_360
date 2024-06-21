package ir.androidcoder.data.remote

import ir.androidcoder.data.model.NewsListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("football360/news/list")
    fun getNewsList(
        @Query("page") page: Int,
        @Query("license") license: String = "14kDIdzjZw8wqCzb9CxOrPtQpgSNmllIDSKqiq"
    ) : NewsListResponse

}