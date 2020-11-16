package com.efisherylite.app.external.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.format.DateFormat
import android.widget.Toast
import com.efisherylite.app.BuildConfig
import java.lang.NumberFormatException
import java.security.MessageDigest
import java.text.NumberFormat
import java.text.SimpleDateFormat
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

fun String?.timestampToDate(): String? {
    var convertDate: String? = null

    try {
        if (!this.isNullOrEmpty()) {
            val timeStamp = this.toLong()
            val calendar = Calendar.getInstance(Locale.getDefault())
            calendar.timeInMillis = timeStamp * 1000L
            convertDate = DateFormat.format("dd-MM-yyyy", calendar).toString()
        }
    } catch (error: Exception) {
        error.printStackTrace()
    }

    return convertDate
}

fun String?.toIDR(): String? {
    return try {
        "Rp ${String.format("%,d", this?.toInt())}"
    } catch (error: Exception) {
        error.printStackTrace()
        "Rp $this"
    }
}

fun String.hashString(): String {
    return MessageDigest
        .getInstance("SHA-256")
        .digest(this.toByteArray())
        .fold("", { str, it -> str + "%02x".format(it) })
}

fun getCurrentDateTime(): String {
    val calendar = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
    return dateFormat.format(calendar)
}