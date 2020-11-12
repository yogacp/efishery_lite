package com.efisherylite.app.data.model.storagelist

import com.google.gson.annotations.SerializedName

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
data class StorageList(
    @SerializedName(value = "area_kota") val areaKota: String?,
    @SerializedName(value = "area_provinsi") val areaProvinsi: String?,
    @SerializedName(value = "tgl_parsed") val tglParsed: String?,
    val komoditas: String?,
    val price: String?,
    val size: String?,
    val timestamp: String?,
    val uuid: String?
)