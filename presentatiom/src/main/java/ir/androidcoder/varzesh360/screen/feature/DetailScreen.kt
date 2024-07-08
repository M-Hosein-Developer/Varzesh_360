package ir.androidcoder.varzesh360.screen.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
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


    Column(
        Modifier
            .fillMaxSize()
    ) {

        if (data != null){

            DetailToolbar{
                navController.popBackStack()
            }

            FullScreenImage(data!!)

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailToolbar(onBackPressed: () -> Unit) {

    TopAppBar(
        title = { Text(text = "Detail") },
        navigationIcon = {
            IconButton(onClick = { onBackPressed.invoke() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
            }
        }
    )

}

@Composable
fun FullScreenImage(data: NewsEntity) {

    AsyncImage(
        model =  data.primary_media.file,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )

}

