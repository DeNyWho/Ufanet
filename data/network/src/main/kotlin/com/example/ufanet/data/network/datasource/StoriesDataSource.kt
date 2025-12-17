package com.example.ufanet.data.network.datasource

import com.example.ufanet.data.network.model.dto.DefaultResponseDTO
import com.example.ufanet.data.network.model.dto.stories.StoriesResponseDTO
import com.example.ufanet.data.network.safeApiCall
import com.example.ufanet.data.network.service.StoriesApiService
import com.example.ufanet.domain.model.common.request.Resource

class StoriesDataSource(
    private val storiesApiService: StoriesApiService,
) {
    suspend fun stories(): Resource<DefaultResponseDTO<StoriesResponseDTO>> {
        return safeApiCall { storiesApiService.stories() }
    }
}