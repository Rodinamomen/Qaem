package com.app.qaem.common.di

import com.app.qaem.common.data.remote.RemoteDataSourceProviderImpl
import com.app.qaem.common.data.remote.provideHttpClient
import com.app.qaem.common.domain.repository.remote.IRemoteDataSourceProvider
import kotlinx.serialization.json.Json
import org.koin.dsl.module


val remoteDataSourceModule = module {
    single<Json> {
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
            encodeDefaults = true
        }
    }
    single {
        provideHttpClient(get())
    }

    single<IRemoteDataSourceProvider> {
        RemoteDataSourceProviderImpl(
            get(),
            get()
        )
    }
}