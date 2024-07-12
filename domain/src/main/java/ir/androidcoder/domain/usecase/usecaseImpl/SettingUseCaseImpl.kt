package ir.androidcoder.domain.usecase.usecaseImpl

import ir.androidcoder.domain.entities.SettingEntity
import ir.androidcoder.domain.repository.SettingRepository
import ir.androidcoder.domain.usecase.SettingUseCase

class SettingUseCaseImpl(private val settingRepository: SettingRepository) : SettingUseCase {

    override suspend fun getSetting(setting: SettingEntity): SettingEntity = settingRepository.getSetting(setting)

}