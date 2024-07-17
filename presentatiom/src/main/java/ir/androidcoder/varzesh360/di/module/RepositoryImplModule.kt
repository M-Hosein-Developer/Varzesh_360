package ir.androidcoder.varzesh360.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.data.local.NewsDao
import ir.androidcoder.data.local.SettingDao
import ir.androidcoder.data.remote.ApiService
import ir.androidcoder.data.repository.NewsRepositoryImpl
import ir.androidcoder.data.repository.SettingRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryImplModule {

    @Provides
    @Singleton
    fun provideNewsRepositoryImpl(apiService: ApiService , dao: NewsDao) : NewsRepositoryImpl = NewsRepositoryImpl(apiService , dao)

    @Provides
    @Singleton
    fun provideSettingRepositoryImpl(dao: SettingDao) : SettingRepositoryImpl = SettingRepositoryImpl(dao)

}