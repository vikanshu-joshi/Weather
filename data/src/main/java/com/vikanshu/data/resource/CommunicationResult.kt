package com.vikanshu.data.resource

sealed class CommunicationResult<out T> {

    data object Loading : CommunicationResult<Nothing>()

    data class LocalData<T>(val data: T) : CommunicationResult<T>()

    data class Success<T>(val data: T) : CommunicationResult<T>()

    data class Error(val error: CommunicationError) : CommunicationResult<Nothing>()

}