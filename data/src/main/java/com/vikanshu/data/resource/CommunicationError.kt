package com.vikanshu.data.resource

enum class CommunicationErrorType {
    UNKNOWN,
    NO_ERROR,
    NO_INTERNET
}

data class CommunicationError(
    val errorType: CommunicationErrorType,
    val errorMessage: String
)