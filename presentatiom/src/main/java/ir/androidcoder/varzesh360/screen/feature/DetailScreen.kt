package ir.androidcoder.varzesh360.screen.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import ir.androidcoder.domain.entities.NewsEntity
import ir.androidcoder.varzesh360.newsIntent.NewsIntent
import ir.androidcoder.varzesh360.newsState.NewsDetailState
import ir.androidcoder.varzesh360.viewModel.NewsViewModel

@Composable
fun DetailScreen(navController: NavHostController, id: String, newsViewModel: NewsViewModel) {

    var data by remember { mutableStateOf<NewsEntity?>(null) }


    LaunchedEffect(Unit) {
        newsViewModel.dataIntent.send(NewsIntent.FetchNewsDataFromDb(id))

        newsViewModel.detailState.collect{

            when(it){

                is NewsDetailState.Idle -> {}
                is NewsDetailState.NewsData -> {
                    data = it.news
                }
                is NewsDetailState.NewsError -> {}

            }

        }
    }


}