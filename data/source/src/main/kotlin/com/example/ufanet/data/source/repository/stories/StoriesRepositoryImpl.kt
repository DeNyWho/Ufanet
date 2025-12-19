package com.example.ufanet.data.source.repository.stories

import com.example.ufanet.data.local.dao.stories.StoryDao
import com.example.ufanet.data.network.datasource.StoriesDataSource
import com.example.ufanet.data.source.mapper.stories.toEntity
import com.example.ufanet.data.source.mapper.stories.toStories
import com.example.ufanet.data.source.mapper.stories.toStory
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

    override fun getStories(query: String?): Flow<StateListWrapper<Story>> {
        return flow {
            val cachedStories = storyDao.getAllStories(query).first()

            if (cachedStories.isEmpty()) {
                when (val result = storiesDataSource.getStories()) {
                    is Resource.Success -> {
                        val stories = result.data.detail.stories
                        if (stories.isNotEmpty()) {
                            val favoriteNames = storyDao.getAllStories(null)
                                .first()
                                .filter { it.isFavourite }
                                .map { it.uniqueName }

                            val entities = stories.map { story ->
                                story.toEntity().copy(
                                    isFavourite = favoriteNames.contains(story.uniqueName)
                                )
                            }
                            storyDao.insertAll(entities)
                        }
                    }
                    is Resource.Error -> {
                        emit(StateListWrapper(error = result.error))
                    }
                    is Resource.Loading -> {
                        emit(StateListWrapper.loading())
                    }
                }
            }

            storyDao.getAllStories(query).collect { entities ->
                emit(StateListWrapper(data = entities.map { it.toStory() }.toImmutableList()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun changeFavourite(uniqueName: String) {
        storyDao.updateFavoriteStatus(uniqueName)
    }
}