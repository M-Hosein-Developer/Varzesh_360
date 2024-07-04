package ir.androidcoder.varzesh360.newsIntent

sealed class NewsIntent {

    data class FetchNewsData(val pageNumber : Int) : NewsIntent()
    data class FetchNewsDataFromDb(val id : String) : NewsIntent()

}