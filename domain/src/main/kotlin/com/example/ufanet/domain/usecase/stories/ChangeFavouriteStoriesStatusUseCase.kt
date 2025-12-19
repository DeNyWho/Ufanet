package com.example.ufanet.domain.usecase.stories

import com.example.ufanet.domain.repository.stories.StoriesRepository

class ChangeFavouriteStoriesStatusUseCase(private val storiesRepository: StoriesRepository) {
    suspend operator fun invoke(uniqueName: String) {
        return storiesRepository.changeFavourite(uniqueName)
    }
}