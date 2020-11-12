package com.efisherylite.app.external.extensions

import android.content.Context
import android.widget.Toast
import com.efisherylite.app.BuildConfig

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
fun debugMode(function: () -> Unit) {
    if (BuildConfig.DEBUG) {
        function()
    }
}

inline fun <T : Any> T?.notNull(f: (it: T) -> Unit) {
    if (this != null) f(this)
}

inline fun String?.notNullOrEmpty(f: (it: String) -> Unit): String? {
    return if (this != null && this.isNotEmpty()) {
        f(this)
        this
    } else null
}

fun Context.toast(msg: String?, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, msg, duration).show()