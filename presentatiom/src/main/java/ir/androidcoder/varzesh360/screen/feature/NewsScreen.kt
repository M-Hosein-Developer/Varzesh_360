@file:Suppress("UNREACHABLE_CODE")

package ir.androidcoder.varzesh360.screen.feature

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ir.androidcoder.domain.entities.NewsEntity
import ir.androidcoder.varzesh360.R
import ir.androidcoder.varzesh360.newsIntent.NewsIntent
import ir.androidcoder.varzesh360.newsState.NewsState
import ir.androidcoder.varzesh360.screen.ui.theme.NoColor
import ir.androidcoder.varzesh360.util.LoadingAnimation
import ir.androidcoder.varzesh360.viewModel.NewsViewModel
import kotlinx.coroutines.delay

@Composable
fun NewsScreen(newsViewModel: NewsViewModel) {

    var counter by remember { mutableIntStateOf(1) }
    var newsData by remember { mutableStateOf<List<NewsEntity>?>(null) }

    LaunchedEffect(counter) {

        newsViewModel.dataIntent.send(NewsIntent.FetchNewsData(counter))

        newsViewModel.newsState.collect {

            when (it) {

                is NewsState.Idle -> {}
                is NewsState.NewsData -> {
                    newsData = it.news
                }

                is NewsState.NewsError -> {
                    Log.v("error", it.error!!)
                }

            }

        }

        delay(2000)

    }


    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 38.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (newsData != null) {


            CostumeToolbar(
                { },
                { }
            )

            SearchNews(
                textValue = newsViewModel.searchValue,
                counter,
                {
                    newsViewModel.searchValue = it
                },
                {
                    counter++
                },
                {
                    counter = 1
                }
            )

            NewsHorizontal(newsData)

            NewsVertical(newsData)


        } else {

            LoadingAnimation()

        }

    }

}

@Composable
fun CostumeToolbar(onProfileClicked: () -> Unit, onAlarmClicked: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {

        AsyncImage(
            model = R.drawable.channels4_profile,
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .clickable { onProfileClicked.invoke() }
        )

        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = null,
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable(true, "", null) { onAlarmClicked.invoke() }
                .border(1.dp, Color.LightGray, RoundedCornerShape(16.dp))
                .padding(8.dp)

        )

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchNews(
    textValue: String,
    counter: Int,
    onValueChange: (String) -> Unit,
    onCounterClicked: () -> Unit,
    onCounterLongClicked: () -> Unit
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedTextField(
            value = textValue,
            onValueChange = { onValueChange.invoke(it) },
            label = { Text(text = "جستجو") },
            shape = RoundedCornerShape(18.dp)
        )

        Surface(
            modifier = Modifier
                .border(1.dp, Color.LightGray, RoundedCornerShape(18.dp))
                .padding(horizontal = 28.dp)
                .padding(vertical = 18.dp)
                .combinedClickable(
                    onClick = { onCounterClicked.invoke() },
                    onLongClick = { onCounterLongClicked.invoke() }
                )
        ) {

            Text(
                text = counter.toString(),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )

        }

    }

}

//Horizontal
@Composable
fun NewsHorizontal(newsData: List<NewsEntity>?) {

    if (newsData != null) {

        val evenIndexedNews = newsData.filterIndexed { index, _ -> index % 2 == 0 }

        LazyRow(
            modifier = Modifier.padding(top = 22.dp)
        ) {
            items(evenIndexedNews.size) { newsItem ->
                AnimatedNewsItem(evenIndexedNews[newsItem], newsItem)
            }
        }
    }

}

@Composable
fun AnimatedNewsItem(newsEntity: NewsEntity, index: Int) {
    var startAnimation by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1500,
            delayMillis = index * 100 // Delay each item for a staggered effect
        ), label = ""
    )

    LaunchedEffect(Unit) {
        delay(100) // Small delay before starting the animation
        startAnimation = true
    }

    NewsItemHorizontal(newsEntity, alpha)
}

@Composable
fun NewsItemHorizontal(newsEntity: NewsEntity, alpha: Float) {

    Box(
        modifier = Modifier
            .height(350.dp)
            .width(379.dp)
            .padding(end = 16.dp)
            .shadow(12.dp, RoundedCornerShape(32.dp), true)
            .alpha(alpha)
    ) {


        AsyncImage(
            model = newsEntity.primary_media.file,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = linearGradient(
                        colors = listOf(NoColor, Color.Black),
                        start = androidx.compose.ui.geometry.Offset(500f, 100f),
                        end = androidx.compose.ui.geometry.Offset(500f, 900f)
                    )
                ),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End

        ) {

            Text(
                text = newsEntity.title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textDirection = TextDirection.Rtl,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                ),
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 24.dp, end = 18.dp, start = 18.dp),
                maxLines = 3
            )

        }

    }

}


//Vertical
@Composable
fun NewsVertical(newsData: List<NewsEntity>?) {


    if (newsData != null) {

        val evenIndexedNews = newsData.filterIndexed { index, _ -> index % 3 == 0 }

        LazyColumn(
            Modifier.padding(top = 16.dp)
        ) {

            items(evenIndexedNews.size) { newsItem ->
                if (newsItem != 0)
                    AnimatedNewsItemVer(evenIndexedNews[newsItem], newsItem)
            }

        }

    }

}

@Composable
fun AnimatedNewsItemVer(newsEntity: NewsEntity, index: Int) {
    var startAnimation by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = index * 100 // Delay each item for a staggered effect
        ), label = ""
    )

    LaunchedEffect(Unit) {
        delay(100) // Small delay before starting the animation
        startAnimation = true
    }

    NewsItemVertical(newsEntity, alpha)
}

@Composable
fun NewsItemVertical(newsEntity: NewsEntity, alpha: Float) {

    Box(
        modifier = Modifier
            .height(350.dp)
            .width(379.dp)
            .padding(bottom = 16.dp)
            .shadow(12.dp, RoundedCornerShape(32.dp), true)
            .alpha(alpha)

    ) {


        AsyncImage(
            model = newsEntity.primary_media.file,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = linearGradient(
                        colors = listOf(NoColor, Color.Black),
                        start = androidx.compose.ui.geometry.Offset(500f, 100f),
                        end = androidx.compose.ui.geometry.Offset(500f, 900f)
                    )
                ),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End

        ) {

            Text(
                text = newsEntity.title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textDirection = TextDirection.Rtl,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                ),
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 24.dp, end = 18.dp, start = 18.dp),
                maxLines = 3
            )

        }

    }

}