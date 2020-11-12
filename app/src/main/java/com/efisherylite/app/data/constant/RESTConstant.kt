package com.efisherylite.app.data.constant

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
object RESTConstant {

    object API_VERSION {
        const val V1 = "v1/"
    }

    object HEADERS {
        const val CONTENT_JSON = "Content-Type: application/json"
    }

    const val SECRET_KEY = "5e1edf521073e315924ceab4"
    const val LIST = "storages/$SECRET_KEY/list"
    const val OPTION_AREA = "storages/$SECRET_KEY/option_area"
    const val OPTION_SIZE = "storages/$SECRET_KEY/option_size"
}