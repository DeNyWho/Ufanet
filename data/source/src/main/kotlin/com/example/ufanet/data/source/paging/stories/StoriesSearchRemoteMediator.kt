package com.example.ufanet.data.source.paging.stories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.ufanet.data.local.dao.stories.StoryDao
import com.example.ufanet.data.local.model.stories.StoryEntity
import com.example.ufanet.data.network.datasource.StoriesDataSource
import com.example.ufanet.data.source.mapper.stories.toEntity
import com.example.ufanet.domain.model.common.request.Resource
import jakarta.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class StoriesRemoteMediator @Inject constructor(
    private val storiesDataSource: StoriesDataSource,
    private val storyDao: StoryDao,
) : RemoteMediator<Int, StoryEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, StoryEntity>
    ): MediatorResult {
        return try {
            if (loadType != LoadType.REFRESH) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            when (val result = storiesDataSource.getStories()) {
                is Resource.Success -> {
                    val stories = result.data.detail.stories

                    if (stories.isNotEmpty()) {
                        val entities = stories.map { it.toEntity() }
                        storyDao.insertAllWithPreserveFavourite(entities)
                    }

                    MediatorResult.Success(endOfPaginationReached = true)
                }
                is Resource.Error -> {
                    MediatorResult.Error(Exception(result.error))
                }
                is Resource.Loading -> {
                    MediatorResult.Success(endOfPaginationReached = false)
                }
            }
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
}