package com.app.qaem.common.data.remote

import com.app.qaem.common.domain.repository.remote.IRemoteDataSourceProvider
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

class RemoteDataSourceProviderImpl(
    private val client: HttpClient,
    private val json: Json
) : IRemoteDataSourceProvider {
    override suspend fun <ResponseBody, RequestBody> request(
        networkMethods: NetworkMethods,
        url: String,
        params: Map<String, Any>?,
        header: Map<String, Any>?,
        requestBody: RequestBody?,
        serializer: KSerializer<ResponseBody>
    ): ResponseBody {
        val response: HttpResponse = when (networkMethods) {
            NetworkMethods.GET -> {
                if (isBaseUrl(url)) {
                    client.get {
                        configureUrl(url)
                        configureParams(params)
                        configureHeader(header)
                    }
                } else {
                    client.get {
                        configureParams(params)
                        configureHeader(header)
                    }
                }

            }

            NetworkMethods.POST -> {
                if (isBaseUrl(url)) {
                    client.post {
                        configureUrl(url)
                        configureParams(params)
                        configureHeader(header)
                        requestBody?.let { setBody(it) }
                    }
                } else {
                    client.post {
                        configureParams(params)
                        configureHeader(header)
                        requestBody?.let { setBody(it) }
                    }
                }
            }

            NetworkMethods.PUT -> {
                if (isBaseUrl(url)) {
                    client.put {
                        configureUrl(url)
                        configureParams(params)
                        configureHeader(header)
                        requestBody?.let { setBody(it) }
                    }
                } else {
                    client.put {
                        configureParams(params)
                        configureHeader(header)
                        requestBody?.let { setBody(it) }
                    }
                }
            }

            NetworkMethods.DELETE -> {
                if (isBaseUrl(url)) {
                    client.delete {
                        configureUrl(url)
                        configureParams(params)
                        configureHeader(header)
                    }
                } else {
                    client.delete {
                        configureParams(params)
                        configureHeader(header)
                    }
                }
            }
        }
        return handleResponse(response, serializer)
    }

    private fun isBaseUrl(path: String): Boolean {
        return path.startsWith("https")
    }

    private fun HttpRequestBuilder.configureUrl(path: String) = url(path)
    private fun HttpRequestBuilder.configureParams(params: Map<String, Any>?) {
        params?.forEach { (key, value) ->
            url.parameters.append(key, value.toString())
        }
    }

    private fun HttpRequestBuilder.configureHeader(header: Map<String, Any>?) {
        header?.forEach { (key, value) ->
            header(key, value)
        }
    }

    private suspend fun <ResponseBody> handleResponse(
        response: HttpResponse,
        serializer: KSerializer<ResponseBody>
    ): ResponseBody {
        val responseBodyText = response.bodyAsText()
        return json.decodeFromString(serializer, responseBodyText)
    }
}