package ir.androidcoder.varzesh360.screen.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import ir.androidcoder.domain.entities.NewsEntity
import ir.androidcoder.varzesh360.newsIntent.NewsIntent
import ir.androidcoder.varzesh360.newsState.NewsDetailState
import ir.androidcoder.varzesh360.screen.ui.theme.NoColor
import ir.androidcoder.varzesh360.viewModel.NewsViewModel

@Composable
fun DetailScreen(navController: NavHostController, id: String, newsViewModel: NewsViewModel , showBottomNav : (Boolean) -> Unit) {

    showBottomNav.invoke(false)

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

    Box(
        Modifier.fillMaxSize()
    ) {

        AsyncImage(
            model = data.primary_media.file,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .background(
                    brush = linearGradient(
                        colors = listOf(NoColor, MaterialTheme.colorScheme.onPrimary),
                        start = androidx.compose.ui.geometry.Offset(500f, 0f),
                        end = androidx.compose.ui.geometry.Offset(500f, 850f)
                    )
                )
                .padding(horizontal = 28.dp)
                .padding(bottom = 72.dp, top = 150.dp)
                .align(Alignment.BottomCenter)
        ) {

            Text(
                text = data.title,
                textAlign = TextAlign.End,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                ),
                modifier = Modifier.align(Alignment.End)
            )

            Text(
                text = data.sub_title,
                textAlign = TextAlign.End,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                ),
                modifier = Modifier.padding(top = 16.dp)
                    .align(Alignment.End)
            )


        }


    }

}

