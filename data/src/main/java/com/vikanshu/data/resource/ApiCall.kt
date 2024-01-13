package com.vikanshu.data.resource

import android.util.Log
import com.vikanshu.data.BuildConfig
import retrofit2.HttpException
import java.io.IOException

/**
 * a generic higher order function to make api calls at on place and handle errors accordingly
 * */
suspend fun <DTO, ENTITY> apiCall(
    operation: suspend () -> DTO,
    converter: (DTO) -> ENTITY,
    isValidResponse: (DTO) -> Boolean
): CommunicationResult<ENTITY> {
    return try {
        val result = operation()
        if (!isValidResponse(result)) throw Exception()
        CommunicationResult.Success(converter(result))
    } catch (e: IOException) {
        Log.e("apiCall", "IOException -> ${e.message}")
        CommunicationResult.Error(
            error = CommunicationError(
                errorType = CommunicationErrorType.NO_INTERNET,
                errorMessage = "No Internet"
            )
        )
    } catch (e: HttpException) {
        Log.e("apiCall", "HttpException -> ${e.message}")
        CommunicationResult.Error(
            error = CommunicationError(
                errorType = CommunicationErrorType.HTTP,
                errorMessage = errorCodeMessages[e.response()?.code()] ?: "Something went wrong"
            )
        )
    } catch (e: Exception) {
        Log.e("apiCall", "Exception -> ${e.message}")
        CommunicationResult.Error(
            error = CommunicationError(
                errorType = CommunicationErrorType.UNKNOWN,
                errorMessage = e.message ?: "Something went wrong"
            )
        )
    }
}


private val errorCodeMessages = mapOf(
    400 to "Bad Request: The request was invalid or cannot be served.",
    401 to "Unauthorized: Authentication is required.",
    403 to "Forbidden: The request is not authorized.",
    404 to "Not Found: The requested resource was not found.",
    405 to "Method Not Allowed: The method is not allowed for the requested resource.",
    409 to "Conflict: The request could not be completed due to a conflict.",
    500 to "Internal Server Error: An unexpected error occurred on the server.",
    502 to "Bad Gateway: The server received an invalid response from an upstream server.",
    503 to "Service Unavailable: The server is currently unavailable.",
    504 to "Gateway Timeout: The server did not receive a timely response from the upstream server."
)