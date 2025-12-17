package com.example.ufanet.data.network

import com.example.ufanet.domain.model.common.request.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal inline fun<reified T> safeApiCall(
    apiCall: () -> Response<T>
): Resource<T> {
    return try {
        val response = apiCall.invoke()

        when {
            response.isSuccessful -> {
                response.body()?.let { body ->
                    Resource.Success(body)
                } ?: Resource.Error("Response body is empty")
            }
            else -> {
                Resource.Error(response.message())
            }
        }
    } catch (e: Exception) {
        when(e) {
            is HttpException -> {
                Resource.Error(e.message())
            }
            is UnknownHostException -> {
                Resource.Error("No internet connection")
            }
            is SocketTimeoutException -> {
                Resource.Error("Request timeout")
            }
            is IOException -> {
                Resource.Error("Network error: ${e.message}")
            }
            else -> {
                Resource.Error("Unknown error: ${e.message}")
            }
        }
    }
}