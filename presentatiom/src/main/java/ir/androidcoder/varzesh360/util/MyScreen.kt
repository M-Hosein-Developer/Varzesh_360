package ir.androidcoder.varzesh360.util

sealed class MyScreen(val route : String) {

    data object NewsScreen : MyScreen("NewsScreen")

}