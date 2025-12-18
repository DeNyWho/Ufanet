package com.example.ufanet.data.source.di

import com.example.ufanet.domain.repository.stories.StoriesRepository
import com.example.ufanet.domain.usecase.stories.ChangeFavouriteStoriesStatusUseCase
import com.example.ufanet.domain.usecase.stories.GetStoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetStoriesUseCase(
        storiesRepository: StoriesRepository,
    ): GetStoriesUseCase {
        return GetStoriesUseCase(
            storiesRepository = storiesRepository,
        )
    }

    @Singleton
    @Provides
    fun provideChangeFavouriteStoriesStatusUseCase(
        storiesRepository: StoriesRepository,
    ): ChangeFavouriteStoriesStatusUseCase {
        return ChangeFavouriteStoriesStatusUseCase(
            storiesRepository = storiesRepository,
        )
    }
}