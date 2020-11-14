package com.efisherylite.app.external.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import com.efisherylite.app.BuildConfig
import java.text.NumberFormat
import java.util.*
import java.util.regex.Pattern


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

fun Context.getConnectionType(): Int {
    var result = 0
    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = manager.getNetworkCapabilities(manager.activeNetwork)
    capabilities.notNull {
        when {
            it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                result = 2
            }
            it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                result = 1
            }
            it.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                result = 3
            }
        }
    }

    return result
}

fun Context.isNetworkActive(): Boolean {
    return (getConnectionType() > 0)
}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun String?.getNumberOnly(): String {
    val regexNumberPattern = "[0-9]"
    val pattern = Pattern.compile(regexNumberPattern)
    val matcher = pattern.matcher(this)

    return if (matcher.find()) {
        matcher.group()
    } else ""
}

fun String?.toIDR(): String? {
    var converted = this

    try {
        val formatter = NumberFormat.getCurrencyInstance(Locale.getDefault())
        formatter.maximumFractionDigits = 0
        formatter.currency = Currency.getInstance("IDR")
        this.notNullOrEmpty {
            converted = formatter.format(this?.getNumberOnly()?.toDouble())
        }
    } catch (error: NumberFormatException) {
        error.printStackTrace()
    }

    return converted
}