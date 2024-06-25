package ir.androidcoder.varzesh360.screen.feature

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ir.androidcoder.varzesh360.R
import ir.androidcoder.varzesh360.viewModel.NewsViewModel

@Composable
fun NewsScreen(newsViewModel: NewsViewModel) {

    val context = LocalContext.current
    var counter by remember { mutableStateOf(1) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 42.dp, bottom = 8.dp)
    ) {

        CostumeToolbar(
            {  },
            {  }
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
                counter = 0
            }
        )

    }

}

@Composable
fun CostumeToolbar(onProfileClicked :() -> Unit , onAlarmClicked :() -> Unit){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {

        AsyncImage(
            model = R.drawable.ic_launcher_background,
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
                .clickable(true, "", null, { onAlarmClicked.invoke() })
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
            .padding(top = 14.dp),
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