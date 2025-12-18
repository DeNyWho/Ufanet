package com.example.ufanet.data.source.repository.stories

import com.example.ufanet.data.network.datasource.StoriesDataSource
import com.example.ufanet.data.source.mapper.stories.toStories
import com.example.ufanet.domain.model.story.Story
import com.example.ufanet.domain.repository.stories.StoriesRepository
import com.example.ufanet.domain.state.StateListWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import com.example.ufanet.domain.model.common.request.Resource
import kotlinx.collections.immutable.toImmutableList

internal class StoriesRepositoryImpl @Inject constructor(
    private val storiesDataSource: StoriesDataSource,
): StoriesRepository {
    override fun getStories(): Flow<StateListWrapper<Story>> {
        return flow {
            emit(StateListWrapper.loading())

            val state = when(val result = storiesDataSource.getStories()) {
                is Resource.Success -> {
                    StateListWrapper(data = result.data.detail.stories.map { it.toStories() }.toImmutableList())
                }
                is Resource.Loading -> {
                    StateListWrapper.loading()
                }
                is Resource.Error -> {
                    StateListWrapper(error = result.error)
                }
            }

            emit(state)
        }.flowOn(Dispatchers.IO)
    }
}