package com.example.ufanet.domain.repository.stories

import com.example.ufanet.domain.model.stories.Stories
import com.example.ufanet.domain.state.StateListWrapper
import kotlinx.coroutines.flow.Flow

interface StoriesRepository {
    fun getStories(): Flow<StateListWrapper<Stories>>
}