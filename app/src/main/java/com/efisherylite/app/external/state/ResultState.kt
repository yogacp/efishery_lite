package com.efisherylite.app.external.state

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
sealed class ResultState<T: Any>(val payload: T? = null, val throwable: Throwable? = null, val message: String? = null) {
    class Loading<T: Any> : ResultState<T>()
    class Idle<T: Any>: ResultState<T>()
    data class Success<T: Any>(val data: T) : ResultState<T>(payload = data)
    data class Error<T: Any>(val th: Throwable) : ResultState<T>(throwable = th)
    data class Message<T: Any>(val msg: String): ResultState<T>(message = msg)
}