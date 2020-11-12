package com.efisherylite.app.koin.module

import com.efisherylite.app.data.network.httpclient.HttpClient
import com.efisherylite.app.domain.dispatcher.AppDispatcher
import com.efisherylite.app.domain.dispatcher.DispatcherProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
val networkModule = module {
    single<DispatcherProvider> { AppDispatcher() }
    single { HttpClient.instance(androidApplication()) }
    single { HttpClient.coroutinesRestClient(get()) }
    single { HttpClient.coroutinesServices(get()) }
}