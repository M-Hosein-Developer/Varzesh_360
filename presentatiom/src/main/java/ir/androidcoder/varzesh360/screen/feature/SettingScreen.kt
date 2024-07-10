package ir.androidcoder.varzesh360.screen.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingScreen(){

    var dynamic by remember { mutableStateOf(false) }
    var dark by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        SettingToolbar{

        }

        ThemeSetting(

            dynamic,
            {
            dynamic = it
            },

            dark,
            {
                dark = it
            },


        )

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

@Composable
fun ThemeSetting(
    dynamicThemeState : Boolean ,
    onDynamicThemeState: (Boolean) -> Unit,
    darkThemeState: Boolean,
    onDarkThemeState: (Boolean) -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
    ) {

        //Dynamic Theme
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ){

            Text(
                text = "تم داینامیک",
                Modifier.padding(end = 12.dp)
            )

            Switch(
                checked = dynamicThemeState,
                onCheckedChange = {
                    onDynamicThemeState.invoke(it)
                },
                thumbContent = if (dynamicThemeState) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                } else {
                    null
                }
            )

        }

        //Dark Theme
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            Text(
                text = "تم دارک",
                Modifier.padding(end = 12.dp)
            )

            Switch(
                checked = darkThemeState,
                onCheckedChange = {
                    onDarkThemeState.invoke(it)
                },
                thumbContent = if (dynamicThemeState) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                } else {
                    null
                }
            )

        }

    }





}