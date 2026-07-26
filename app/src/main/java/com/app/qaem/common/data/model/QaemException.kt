package com.app.qaem.common.data.model

sealed class QaemException(message: String?) : Exception(message) {
    sealed class Network(override val message: String?) : QaemException(message) {
        data class Repeatable(override val message: String? = null) : Network(message)
        data class Unhandled(val errorCode: Int, override val message: String? = null) :
            Network(message = "Network Unhandled error with code:${errorCode}, and the failure reason: $message")
    }

    sealed class Client(override val message: String?) : QaemException(message) {
        data object Unauthorized : Client("Unauthorized")
        data class ResponseValidation(
            val errors: Map<ErrorKey, String>,
            override val message: String? = null
        ) : Client(message)

        data class Unhandled(val errorCode: Int, override val message: String? = null) :
            Client(message = "Client Unhandled error with code:${errorCode}, and the failure reason: $message")
    }

    sealed class Server(override val message: String?) : QaemException(message) {
        data class InternalServerError(
            override val message: String? = null,
            val httpErrorCode: Int
        ) : Server(message = "Internal server error with code:${httpErrorCode}, and the failure reason: $message")
    }

    sealed class Unknown(override val message: String?) : QaemException(message) {

    }
}