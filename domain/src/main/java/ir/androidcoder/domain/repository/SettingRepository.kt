package ir.androidcoder.domain.repository

import ir.androidcoder.domain.entities.SettingEntity

interface SettingRepository {

    suspend fun setSetting(setting : SettingEntity)
    suspend fun getSetting() : SettingEntity
}