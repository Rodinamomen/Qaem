package com.app.qaem.common.domain.repository.remote

import com.app.qaem.common.data.remote.NetworkMethods
import kotlinx.serialization.KSerializer

interface IRemoteDataSourceProvider {
    suspend fun <ResponseBody, RequestBody> request(
        networkMethods: NetworkMethods,
        url: String,
        params: Map<String, Any>? = null,
        header: Map<String, Any>? = null,
        requestBody: RequestBody? = null,
        serializer: KSerializer<ResponseBody>
    ): ResponseBody
}