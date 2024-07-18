package ir.androidcoder.domain.usecase

import ir.androidcoder.domain.entities.SettingEntity

interface SettingUseCase {

    suspend fun setSetting(setting : SettingEntity)
    suspend fun getSetting() : SettingEntity
}