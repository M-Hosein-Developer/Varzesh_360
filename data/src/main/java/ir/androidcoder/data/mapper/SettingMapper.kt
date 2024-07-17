package ir.androidcoder.data.mapper

import ir.androidcoder.data.local.entities.SettingEntity

fun SettingEntity.toDomainSetting() = ir.androidcoder.domain.entities.SettingEntity(

    darkTheme = darkTheme,
    dynamicTheme = dynamicTheme,

)

fun ir.androidcoder.domain.entities.SettingEntity.toEntitySetting() = SettingEntity(

    darkTheme = darkTheme,
    dynamicTheme = dynamicTheme,

)