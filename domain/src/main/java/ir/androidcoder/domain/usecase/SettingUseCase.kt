package ir.androidcoder.domain.usecase

import ir.androidcoder.domain.entities.SettingEntity

interface SettingUseCase {

    suspend fun getSetting() : SettingEntity

}