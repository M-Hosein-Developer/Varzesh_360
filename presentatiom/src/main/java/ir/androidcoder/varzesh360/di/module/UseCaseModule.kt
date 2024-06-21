package ir.androidcoder.varzesh360.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.data.repository.NewsRepositoryImpl
import ir.androidcoder.domain.repository.NewsRepository
import ir.androidcoder.domain.usecase.usecaseImpl.NewsUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {


    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepository: NewsRepository) : NewsUseCaseImpl = NewsUseCaseImpl(newsRepository)

}