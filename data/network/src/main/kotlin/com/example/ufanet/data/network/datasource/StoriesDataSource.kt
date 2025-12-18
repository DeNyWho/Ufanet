package com.example.ufanet.data.network.datasource

import com.example.ufanet.data.network.model.dto.DefaultResponseDTO
import com.example.ufanet.data.network.model.dto.stories.StoriesResponseDTO
import com.example.ufanet.data.network.safeApiCall
import com.example.ufanet.data.network.service.StoriesApiService
import com.example.ufanet.domain.model.common.request.Resource
import javax.inject.Inject

class StoriesDataSource @Inject constructor(
    private val storiesApiService: StoriesApiService,
) {
    suspend fun getStories(): Resource<DefaultResponseDTO<StoriesResponseDTO>> {
        return safeApiCall { storiesApiService.getStories() }
    }
}