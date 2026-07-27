package com.app.qaem.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.app.qaem.common.data.repository.local.LocalDataSourceProvider
import com.app.qaem.common.domain.repository.local.ILocalDataSourceProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val localDataSourceModule = module {

    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create {
            get<Context>().preferencesDataStoreFile("app_datastore")
        }
    }

    singleOf(::LocalDataSourceProvider) bind ILocalDataSourceProvider::class
}