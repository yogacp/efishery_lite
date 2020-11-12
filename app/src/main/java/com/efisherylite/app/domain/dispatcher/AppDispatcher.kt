package com.efisherylite.app.domain.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
class AppDispatcher : DispatcherProvider {
    override fun ui(): CoroutineDispatcher = Dispatchers.Main
    override fun main(): CoroutineDispatcher = Dispatchers.Default
    override fun io(): CoroutineDispatcher = Dispatchers.IO
}