package ir.androidcoder.varzesh360.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.androidcoder.domain.entities.SettingEntity
import ir.androidcoder.domain.usecase.SettingUseCase
import ir.androidcoder.varzesh360.newsIntent.SettingIntent
import ir.androidcoder.varzesh360.newsState.SettingState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val settingUseCase: SettingUseCase) : ViewModel(){

    val dataIntent = Channel<SettingIntent>()

    private val _settingState = MutableStateFlow<SettingState>(SettingState.Idle)
    val settingState : StateFlow<SettingState> get() = _settingState

    init {
        handleIntent()
    }

    private fun handleIntent() = viewModelScope.launch {

        dataIntent.consumeAsFlow().collect {
            when (it) {
                is SettingIntent.FetchSettingData -> themeSetting(it.setting)
            }
        }

    }

    private fun themeSetting(data: SettingEntity) = viewModelScope.launch {

        try {
            _settingState.value = SettingState.SettingData(settingUseCase.getSetting(data))
        }catch (e : Exception){
            _settingState.value = SettingState.SettingError(e.localizedMessage)
        }

    }

}
