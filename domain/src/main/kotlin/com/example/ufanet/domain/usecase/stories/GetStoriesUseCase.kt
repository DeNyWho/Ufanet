package com.example.ufanet.domain.usecase.stories

import androidx.paging.PagingData
import com.example.ufanet.domain.model.story.Story
import com.example.ufanet.domain.repository.stories.StoriesRepository
import kotlinx.coroutines.flow.Flow

class GetStoriesUseCase(private val storiesRepository: StoriesRepository) {
    operator fun invoke(
        limit: Int = 20,
        query: String?,
    ): Flow<PagingData<Story>> {
        return storiesRepository.getStories(
            limit = limit,
            query = query,
        )
    }
}