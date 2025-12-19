package com.example.ufanet.domain.repository.stories

import androidx.paging.PagingData
import com.example.ufanet.domain.model.story.Story
import kotlinx.coroutines.flow.Flow

interface StoriesRepository {
    fun getStories(limit: Int, query: String? = null): Flow<PagingData<Story>>
    suspend fun changeFavourite(uniqueName: String)
}