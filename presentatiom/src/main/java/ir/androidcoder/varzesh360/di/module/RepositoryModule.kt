package ir.androidcoder.varzesh360.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.data.repository.NewsRepositoryImpl
import ir.androidcoder.domain.repository.NewsRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideNewsRepository(newsRepositoryImpl: NewsRepositoryImpl) : NewsRepository

}