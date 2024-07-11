package ir.androidcoder.varzesh360.newsIntent

import ir.androidcoder.domain.entities.SettingEntity

sealed class SettingIntent {

    data class FetchSettingData(val setting : SettingEntity) : SettingIntent()

}