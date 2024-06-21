package ir.androidcoder.varzesh360.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.data.remote.ApiService
import ir.androidcoder.data.repository.NewsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryImplModule {

    @Provides
    @Singleton
    fun provideNewsRepositoryImpl(apiService: ApiService) : NewsRepositoryImpl = NewsRepositoryImpl(apiService)

}