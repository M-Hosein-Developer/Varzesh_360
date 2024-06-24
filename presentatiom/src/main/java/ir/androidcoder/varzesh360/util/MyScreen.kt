package ir.androidcoder.varzesh360.util

sealed class MyScreen(route : String) {

    data object NewsScreen : MyScreen("NewsScreen")

}