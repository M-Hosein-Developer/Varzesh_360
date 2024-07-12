package ir.androidcoder.domain.repository

import ir.androidcoder.domain.entities.SettingEntity

interface SettingRepository {

    suspend fun getSetting(setting : SettingEntity) : SettingEntity

}