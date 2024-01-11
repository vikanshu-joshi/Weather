package com.vikanshu.data.resource

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
        CommunicationResult.Error(
            error = CommunicationError(
                errorType = CommunicationErrorType.NO_INTERNET,
                errorMessage = "No Internet"
            )
        )
    } catch (e: Exception) {
        CommunicationResult.Error(
            error = CommunicationError(
                errorType = CommunicationErrorType.UNKNOWN,
                errorMessage = e.localizedMessage ?: "Something went wrong"
            )
        )
    }
}