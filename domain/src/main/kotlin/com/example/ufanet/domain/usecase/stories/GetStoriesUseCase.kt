package com.example.ufanet.domain.usecase.stories

import com.example.ufanet.domain.model.stories.Stories
import com.example.ufanet.domain.repository.stories.StoriesRepository
import com.example.ufanet.domain.state.StateListWrapper
import kotlinx.coroutines.flow.Flow

class GetStoriesUseCase(private val storiesRepository: StoriesRepository) {
    operator fun invoke(): Flow<StateListWrapper<Stories>> {
        return storiesRepository.getStories()
    }
}