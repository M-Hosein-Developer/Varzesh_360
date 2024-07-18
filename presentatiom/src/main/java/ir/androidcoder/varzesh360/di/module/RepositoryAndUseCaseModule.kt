package ir.androidcoder.varzesh360.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.data.repository.NewsRepositoryImpl
import ir.androidcoder.data.repository.SettingRepositoryImpl
import ir.androidcoder.domain.repository.NewsRepository
import ir.androidcoder.domain.repository.SettingRepository
import ir.androidcoder.domain.usecase.NewsUseCase
import ir.androidcoder.domain.usecase.SettingUseCase
import ir.androidcoder.domain.usecase.usecaseImpl.NewsUseCaseImpl
import ir.androidcoder.domain.usecase.usecaseImpl.SettingUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryAndUseCaseModule {

    //---Repository---------------------------------------------------------------------------------
    @Binds
    abstract fun provideNewsRepository(newsRepositoryImpl: NewsRepositoryImpl) : NewsRepository

    @Binds
    abstract fun provideSettingRepository(settingRepositoryImpl: SettingRepositoryImpl) : SettingRepository



    //---UseCase------------------------------------------------------------------------------------
    @Binds
    abstract fun provideNewsUseCase(newsUseCaseImpl: NewsUseCaseImpl) : NewsUseCase

    @Binds
    abstract fun provideSettingUseCase(settingUseCaseImpl: SettingUseCaseImpl) : SettingUseCase


}