package ir.androidcoder.varzesh360.screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.androidcoder.varzesh360.screen.feature.NewsScreen
import ir.androidcoder.varzesh360.screen.feature.SettingScreen
import ir.androidcoder.varzesh360.screen.ui.theme.Varzesh360Theme
import ir.androidcoder.varzesh360.util.MyScreen
import ir.androidcoder.varzesh360.viewModel.NewsViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Varzesh360Theme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

                    MainUi(newsViewModel)

                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainUi(newsViewModel: NewsViewModel) {

    val navController = rememberNavController()
    var isVisible by remember { mutableStateOf(true) }


    NavHost(navController = navController, startDestination = MyScreen.NewsScreen.route) {

        composable(MyScreen.NewsScreen.route) {
            NewsScreen(newsViewModel){ isVisible = it }
        }

        composable(MyScreen.SettingScreen.route) {
            SettingScreen()
        }

    }


//    if (isVisible) {
//        NavigationBar(
//            modifier = Modifier
//                .wrapContentSize(align = Alignment.BottomCenter)
//                .height(112.dp)
//                .padding(horizontal = 42.dp)
//                .padding(bottom = 32.dp, top = 12.dp)
//                .clip(RoundedCornerShape(32.dp))
//        ) {
//
//            NavigationBarItem(
//                modifier = Modifier
//                    .align(Alignment.CenterVertically)
//                    .padding(top = 24.dp),
//                selected = false,
//                onClick = { navController.navigate(MyScreen.NewsScreen.route) },
//                icon = {
//                    Icon(
//                        imageVector = Icons.Outlined.Home,
//                        contentDescription = null
//                    )
//                },
//                label = {
//                    Text(text = "Home")
//                }
//            )
//
//            NavigationBarItem(
//                modifier = Modifier
//                    .align(Alignment.CenterVertically)
//                    .padding(top = 24.dp),
//                selected = false,
//                onClick = { navController.navigate(MyScreen.SettingScreen.route) },
//                icon = {
//                    Icon(
//                        imageVector = Icons.Outlined.Settings,
//                        contentDescription = null
//                    )
//                },
//                label = {
//                    Text(text = "Setting")
//                }
//            )
//
//        }
//    }


    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(durationMillis = 3000)
        ),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(durationMillis = 3000)
        )
    ) {
        NavigationBar(
            modifier = Modifier
                .wrapContentSize(align = Alignment.BottomCenter)
                .height(112.dp)
                .padding(horizontal = 42.dp)
                .padding(bottom = 32.dp, top = 12.dp)
                .clip(RoundedCornerShape(32.dp))
        ) {
            NavigationBarItem(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 24.dp),
                selected = false,
                onClick = { navController.navigate(MyScreen.NewsScreen.route) },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = "Home")
                }
            )

            NavigationBarItem(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 24.dp),
                selected = false,
                onClick = { navController.navigate(MyScreen.SettingScreen.route) },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = "Setting")
                }
            )
        }
    }
}

