package ir.androidcoder.varzesh360.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.data.repository.NewsRepositoryImpl
import ir.androidcoder.domain.repository.NewsRepository
import ir.androidcoder.domain.usecase.NewsUseCase
import ir.androidcoder.domain.usecase.usecaseImpl.NewsUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryAndUseCaseModule {

    //---Repository---------------------------------------------------------------------------------
    @Binds
    abstract fun provideNewsRepository(newsRepositoryImpl: NewsRepositoryImpl) : NewsRepository



    //---UseCase------------------------------------------------------------------------------------
    @Binds
    abstract fun provideNewsUseCase(newsUseCaseImpl: NewsUseCaseImpl) : NewsUseCase


}