package com.example.ufanet.data.source.repository.stories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.ufanet.data.local.dao.stories.StoryDao
import com.example.ufanet.data.network.datasource.StoriesDataSource
import com.example.ufanet.data.source.mapper.stories.toEntity
import com.example.ufanet.data.source.mapper.stories.toStories
import com.example.ufanet.data.source.mapper.stories.toStory
import com.example.ufanet.data.source.paging.stories.StoriesRemoteMediator
import com.example.ufanet.domain.model.story.Story
import com.example.ufanet.domain.repository.stories.StoriesRepository
import com.example.ufanet.domain.state.StateListWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import com.example.ufanet.domain.model.common.request.Resource
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext

internal class StoriesRepositoryImpl @Inject constructor(
    private val storiesDataSource: StoriesDataSource,
    private val storyDao: StoryDao,
) : StoriesRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getStories(limit: Int, query: String?): Flow<PagingData<Story>> {
        return Pager(
            config = PagingConfig(
                pageSize = limit,
                enablePlaceholders = false,
                prefetchDistance = 3,
                initialLoadSize = 20
            ),
            remoteMediator = StoriesRemoteMediator(
                storiesDataSource = storiesDataSource,
                storyDao = storyDao
            ),
            pagingSourceFactory = {
                storyDao.pagingSource(query)
            }
        ).flow.map { pagingData ->
            pagingData.map { entity -> entity.toStory() }
        }
    }

    override suspend fun changeFavourite(uniqueName: String) {
        storyDao.updateFavoriteStatus(uniqueName)
    }
}