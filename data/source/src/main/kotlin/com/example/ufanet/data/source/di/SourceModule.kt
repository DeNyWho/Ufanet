package com.example.ufanet.data.source.di

import com.example.ufanet.data.network.datasource.StoriesDataSource
import com.example.ufanet.data.source.repository.stories.StoriesRepositoryImpl
import com.example.ufanet.domain.repository.stories.StoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SourceModule {
    @Singleton
    @Provides
    fun provideStoriesRepository(
        storiesDataSource: StoriesDataSource,
    ): StoriesRepository {
        return StoriesRepositoryImpl(
            storiesDataSource = storiesDataSource,
        )
    }
}