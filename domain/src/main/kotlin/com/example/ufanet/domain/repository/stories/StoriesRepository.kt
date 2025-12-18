package com.example.ufanet.domain.repository.stories

import com.example.ufanet.domain.model.story.Story
import com.example.ufanet.domain.state.StateListWrapper
import kotlinx.coroutines.flow.Flow

interface StoriesRepository {
    fun getStories(): Flow<StateListWrapper<Story>>
}