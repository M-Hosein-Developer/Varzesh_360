package ir.androidcoder.varzesh360.screen.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingScreen(){


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        SettingToolbar{

        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingToolbar(onResetClicked :() -> Unit){

    TopAppBar(
        title = {
            Text(text = "Setting")
        },
        actions = {
            TextButton(
                onClick = { onResetClicked.invoke() }
                ) {
                Text(
                    text = "Reset Setting"
                )
            }
        },
    )
}