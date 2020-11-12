package com.efisherylite.app.data.dao.storagelist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
@Entity(tableName = "StorageList")
data class StorageListEntity(
    @PrimaryKey val uuid: String,
    @ColumnInfo(name = "area_kota") val areaKota: String?,
    @ColumnInfo(name = "area_provinsi") val areaProvinsi: String?,
    @ColumnInfo(name = "tgl_parsed") val tglParsed: String?,
    val komoditas: String?,
    val price: String?,
    val size: String?,
    val timestamp: String?
)