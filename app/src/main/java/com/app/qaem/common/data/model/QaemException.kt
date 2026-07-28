package com.app.qaem.common.data.model

import com.app.qaem.common.domain.model.IErrorKey
import com.app.qaem.common.domain.model.RequestErrorKeyValues
import kotlinx.serialization.Serializable

@Serializable
sealed class QaemException(
    @Transient override val message: String? = null
) : Exception(message) {
    @Serializable
    sealed class Network(@Transient override val message: String? = null) : QaemException(message) {
        data class Repeatable(@Transient override val message: String? = null) : Network(message)
        data class Unhandled(val errorCode: Int, @Transient override val message: String? = null) :
            Network(message = "Network Unhandled error with code:${errorCode}, and the failure reason: $message")
    }
    sealed class Local(@Transient override val message: String? = null) : QaemException(message) {
        data class RequestValidation(
            override val message: String? = null,
            val errors: Map<IErrorKey, RequestErrorKeyValues> = hashMapOf()
        ) : Local(message)
        data class IOOperation(override val message: String? = null) : Local(message)
        data class Unhandled(val errorCode: Int, override val message: String? = null) :
            Local(message = "Local Unhandled error with code:${errorCode}, and the failure reason: $message")
    }

    @Serializable
    sealed class Client(@Transient override val message: String? = null) : QaemException(message) {
        data object Unauthorized : Client("Unauthorized")
        data class ResponseValidation(val errors: Map<ErrorKey, String>, @Transient override val message: String? = null) : Client(message)
        data class Unhandled(val errorCode: Int, @Transient override val message: String? = null) : Client(message = "Client Unhandled error with code:${errorCode}, and the failure reason: $message")
    }

    @Serializable
    sealed class Server(@Transient override val message: String? = null) : QaemException(message) {
        data class InternalServerError(@Transient override val message: String? = null, val httpErrorCode: Int) : Server(message = "Internal server error with code:${httpErrorCode}, and the failure reason: $message")
    }

    @Serializable
    sealed class Unknown(@Transient override val message: String? = null) : QaemException(message)
}