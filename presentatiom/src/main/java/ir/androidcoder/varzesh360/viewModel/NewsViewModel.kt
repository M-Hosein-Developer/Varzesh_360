package ir.androidcoder.varzesh360.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.androidcoder.domain.usecase.NewsUseCase
import ir.androidcoder.varzesh360.newsIntent.NewsIntent
import ir.androidcoder.varzesh360.newsState.NewsDetailState
import ir.androidcoder.varzesh360.newsState.NewsState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {

    val dataIntent = Channel<NewsIntent>()

    //News State
    private val _newsState = MutableStateFlow<NewsState>(NewsState.Idle)
    val newsState : StateFlow<NewsState> get() = _newsState

    //Detail State
    private val _detailState = MutableStateFlow<NewsDetailState>(NewsDetailState.Idle)
    val detailState : StateFlow<NewsDetailState> get() = _detailState

    init {
        handleIntent()
    }

    private fun handleIntent() {

        viewModelScope.launch {

            dataIntent.consumeAsFlow().collect {

                when (it) {
                    is NewsIntent.FetchNewsData -> newsDate(it.pageNumber)
                    is NewsIntent.FetchNewsDataFromDb -> newsDateFromDb(it.id)
                }

            }

        }

    }

    private fun newsDate(pageNumber: Int) = viewModelScope.launch {

         try {
             _newsState.value = NewsState.NewsData(newsUseCase.getNews(pageNumber))
         }catch (e : Exception){
             _newsState.value = NewsState.NewsError(e.localizedMessage)
         }

    }


    private fun newsDateFromDb(id: String) = viewModelScope.launch {

        try {
            _detailState.value = NewsDetailState.NewsData(newsUseCase.getNewsFromDbById(id))
        }catch (e : Exception){
            _detailState.value = NewsDetailState.NewsError(e.localizedMessage)
        }

    }

}