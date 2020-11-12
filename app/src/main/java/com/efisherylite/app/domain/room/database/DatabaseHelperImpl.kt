package com.efisherylite.app.domain.room.database

import androidx.lifecycle.LiveData
import com.efisherylite.app.data.dao.storagelist.StorageListEntity

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    /**
     * Storage List Database
     */
    override fun getAllStorage(): LiveData<List<StorageListEntity>> = appDatabase.storageDao().getAllStorage()
    override fun insertAllStorage(storages: List<StorageListEntity>) = appDatabase.storageDao().insertAll(storages)
    override fun deleteStorage(storage: StorageListEntity) = appDatabase.storageDao().deleteStorage(storage)
    override fun deleteAllStorage() = appDatabase.storageDao().deleteAllStorage()

}