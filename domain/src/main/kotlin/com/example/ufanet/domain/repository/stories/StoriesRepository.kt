package com.example.ufanet.domain.repository.stories

import com.example.ufanet.domain.model.story.Story
import com.example.ufanet.domain.state.StateListWrapper
import kotlinx.coroutines.flow.Flow

interface StoriesRepository {
    fun getStories(query: String? = null): Flow<StateListWrapper<Story>>
    suspend fun changeFavourite(uniqueName: String)
}