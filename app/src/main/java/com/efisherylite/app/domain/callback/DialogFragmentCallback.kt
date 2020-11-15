package com.efisherylite.app.domain.callback

/**
 * Created by Yoga C. Pranata on 15/11/2020.
 * Android Engineer
 */
interface DialogFragmentCallback {
    fun <T> onResultDialog(data: T)
}