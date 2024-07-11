package ir.androidcoder.varzesh360.newsState

import ir.androidcoder.domain.entities.SettingEntity

sealed class SettingState {

    data object Idle : SettingState()
    data class SettingData(val setting : SettingEntity) : SettingState()
    data class SettingError(val error : String?) : SettingState()

}