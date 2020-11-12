package com.efisherylite.app.domain.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
interface DispatcherProvider {
    fun ui(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
}