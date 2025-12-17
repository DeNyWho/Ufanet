package com.example.ufanet.data.network.service

import com.example.ufanet.data.network.model.dto.DefaultResponseDTO
import com.example.ufanet.data.network.model.dto.stories.StoriesResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface StoriesApiService {
    @GET("api/v0/stories")
    suspend fun getStories(): Response<DefaultResponseDTO<StoriesResponseDTO>>
}