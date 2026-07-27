package com.app.qaem.common.data.model

import com.app.qaem.common.domain.model.IErrorKey
import com.app.qaem.common.domain.model.RequestErrorKeyValues
import kotlinx.serialization.Serializable

@Serializable
sealed class QaemException(
    @Transient override val message: String? = null
) : Exception(message) {
    @Serializable
    sealed class Local(@Transient override val message: String? = null) : QaemException(message) {
        data class RequestValidation(
            override val message: String? = null,
            val errors: Map<IErrorKey, RequestErrorKeyValues> = hashMapOf()
        ) : Local(message)

        data class IOOperation(override val message: String? = null) : Local(message)
        data class Unhandled(val errorCode: Int, override val message: String? = null) :
            Local(message = "Local Unhandled error with code:${errorCode}, and the failure reason: $message")
    }

}