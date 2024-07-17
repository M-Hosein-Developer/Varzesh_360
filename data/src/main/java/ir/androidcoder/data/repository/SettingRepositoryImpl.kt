package ir.androidcoder.data.repository

import ir.androidcoder.data.local.SettingDao
import ir.androidcoder.data.mapper.toDomainSetting
import ir.androidcoder.data.mapper.toEntitySetting
import ir.androidcoder.domain.entities.SettingEntity
import ir.androidcoder.domain.repository.SettingRepository

class SettingRepositoryImpl(private val dao : SettingDao) : SettingRepository {

    override suspend fun setSetting(setting: SettingEntity) = dao.insertThemeSetting(setting.toEntitySetting())

    override suspend fun getSetting(): SettingEntity = dao.getThemeSetting().toDomainSetting()


}