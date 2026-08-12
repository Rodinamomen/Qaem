package com.app.qaem.common.data.model

import com.app.qaem.common.domain.model.IErrorKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APIErrorResponse (
    @SerialName("message")
    val message: String,
    @SerialName("errors")
    val errors: Map<IErrorKey, List<String>>?
)