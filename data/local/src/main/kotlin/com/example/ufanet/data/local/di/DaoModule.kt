package com.example.ufanet.data.local.di

import com.example.ufanet.data.local.UfanetDatabase
import com.example.ufanet.data.local.dao.stories.StoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    @Singleton
    fun provideStoryDao(
        database: UfanetDatabase,
    ): StoryDao = database.storyDao()
}