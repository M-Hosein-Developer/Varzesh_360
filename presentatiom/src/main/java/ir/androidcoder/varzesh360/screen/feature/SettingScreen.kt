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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.androidcoder.domain.entities.SettingEntity
import ir.androidcoder.varzesh360.newsIntent.SettingIntent
import ir.androidcoder.varzesh360.newsState.SettingState
import ir.androidcoder.varzesh360.viewModel.SettingViewModel
import kotlinx.coroutines.launch

@Composable
fun SettingScreen(settingViewModel: SettingViewModel) {

    var dynamic by remember { mutableStateOf(false) }
    var dark by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {

        settingViewModel.dataIntent.send(SettingIntent.FetchSettingData1)

        settingViewModel.settingState.collect {
            when (it) {

                is SettingState.Idle -> {}
                is SettingState.SettingData -> {
                    dynamic = it.setting.dynamicTheme
                    dark = it.setting.darkTheme
                }

                is SettingState.SettingError -> {}

            }
        }

    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        SettingToolbar{
            scope.launch {
                settingViewModel.dataIntent.send(SettingIntent.FetchSettingData(SettingEntity(id = 0, dynamicTheme = false, darkTheme = false)))
            }
        }

        ThemeSetting(

            dynamic,
            {
                dynamic = it
                scope.launch {
                    settingViewModel.dataIntent.send(
                        SettingIntent.FetchSettingData(
                            SettingEntity(
                                0,
                                it,
                                dark
                            )
                        )
                    )
                }
            },

            dark,
            {
                dark = it
                scope.launch {
                    settingViewModel.dataIntent.send(
                        SettingIntent.FetchSettingData(
                            SettingEntity(
                                0,
                                dynamic,
                                it
                            )
                        )
                    )
                }
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
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
                thumbContent = if (darkThemeState) {
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