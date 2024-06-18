package ir.androidcoder.varzesh360.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("football360/news/info")
    fun getNewsInfo(
        @Query("id") newsId: String = "news_id",
        @Query("license") license: String = "14kDIdzjZw8wqCzb9CxOrPtQpgSNmllIDSKqiq"
    )

}