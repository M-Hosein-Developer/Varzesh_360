package ir.androidcoder.varzesh360.screen.feature

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(navController: NavHostController, int: String) {


    Text(text = int)

}