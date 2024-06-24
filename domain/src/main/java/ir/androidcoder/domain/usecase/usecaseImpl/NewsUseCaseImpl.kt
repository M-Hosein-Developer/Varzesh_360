package ir.androidcoder.domain.usecase.usecaseImpl

import ir.androidcoder.domain.entities.NewsEntity
import ir.androidcoder.domain.repository.NewsRepository
import ir.androidcoder.domain.usecase.NewsUseCase

class NewsUseCaseImpl(private val newsRepository: NewsRepository) : NewsUseCase {

    override suspend fun getNews(pageNumber : Int): List<NewsEntity> = newsRepository.getNews(pageNumber)

}