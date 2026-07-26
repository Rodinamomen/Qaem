package com.app.qaem.common.data.remote

import com.app.qaem.common.data.model.ErrorKey
import com.app.qaem.common.data.model.QaemException
import com.app.qaem.common.data.model.APIErrorResponse
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun provideHttpClient(
    json: Json
): HttpClient {
    return HttpClient {


        expectSuccess = true
        install(ContentNegotiation) {
            json()
        }
        install(Logging) {
            level = LogLevel.ALL
        }
        install(io.ktor.client.plugins.HttpTimeout) {
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 60_000
            socketTimeoutMillis = 20_000
        }
        defaultRequest {
            url("")
            contentType(ContentType.Application.Json)
        }
        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, request ->
                if (exception is ResponseException) throw handleResponseException(
                    exception.response,
                    json
                )
            }
        }
    }
}

private suspend fun handleResponseException(
    response: HttpResponse,
    json: Json
): QaemException {
    when (val responseCode = response.status.value) {
        HttpStatusCode.Unauthorized.value -> throw QaemException.Client.Unauthorized
        HttpStatusCode.UnprocessableEntity.value -> {
            val responseBodyText = response.bodyAsText()
            return responseValidationMapping(
                json.decodeFromString<APIErrorResponse>(
                    responseBodyText
                )
            )
        }

        HttpStatusCode.InternalServerError.value -> throw QaemException.Server.InternalServerError(
            httpErrorCode = responseCode, message = response.status.description
        )

        else -> return QaemException.Client.Unhandled(
            errorCode = responseCode, message = response.status.description
        )
    }

}

private fun responseValidationMapping(errorResponse: APIErrorResponse): QaemException {
    return QaemException.Client.ResponseValidation(
        errors = errorResponse.errors?.mapNotNull { (key, value) ->
            if (key == ErrorKey.UNKNOWN) null else key to value.first()
        }?.toMap() ?: emptyMap(), message = errorResponse.message
    )
}