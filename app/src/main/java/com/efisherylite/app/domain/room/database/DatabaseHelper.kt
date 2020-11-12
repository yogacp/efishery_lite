package com.efisherylite.app.domain.room.database

import androidx.lifecycle.LiveData
import com.efisherylite.app.data.dao.storagelist.StorageListEntity

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
interface DatabaseHelper {
    /**
     * Storage List Database
     */
    fun getAllStorage(): LiveData<List<StorageListEntity>>
    fun insertAllStorage(storages: List<StorageListEntity>)
    fun deleteStorage(storage: StorageListEntity)
    fun deleteAllStorage()

}