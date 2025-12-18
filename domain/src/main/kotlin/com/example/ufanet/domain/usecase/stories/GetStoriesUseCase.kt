package com.example.ufanet.domain.usecase.stories

import com.example.ufanet.domain.model.story.Story
import com.example.ufanet.domain.repository.stories.StoriesRepository
import com.example.ufanet.domain.state.StateListWrapper
import kotlinx.coroutines.flow.Flow

class GetStoriesUseCase(private val storiesRepository: StoriesRepository) {
    operator fun invoke(query: String?): Flow<StateListWrapper<Story>> {
        return storiesRepository.getStories(query)
    }
}