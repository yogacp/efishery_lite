package com.efisherylite.app.domain.room.database

import androidx.lifecycle.LiveData
import com.efisherylite.app.data.dao.optionarea.OptionAreaEntity
import com.efisherylite.app.data.dao.optionsize.OptionSizeEntity
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
    override fun searchStorageByCommodity(query: String?): LiveData<List<StorageListEntity>> = appDatabase.storageDao().searchStorageByCommodity(query)
    override suspend fun insertAllStorage(storages: List<StorageListEntity>) = appDatabase.storageDao().insertAll(storages)
    override suspend fun deleteStorage(storage: StorageListEntity) = appDatabase.storageDao().deleteStorage(storage)
    override suspend fun deleteAllStorage() = appDatabase.storageDao().deleteAllStorage()

    /**
     * Option Area Database
     */
    override fun getAllAreas(): LiveData<List<OptionAreaEntity>> = appDatabase.optionAreaDao().getAllData()
    override suspend fun insertAllAreas(areas: List<OptionAreaEntity>) = appDatabase.optionAreaDao().insertAll(areas)
    override suspend fun deleteArea(area: OptionAreaEntity) = appDatabase.optionAreaDao().delete(area)
    override suspend fun deleteAllAreas() = appDatabase.optionAreaDao().deleteAllData()

    /**
     * Option Size Database
     */
    override fun getAllSizes(): LiveData<List<OptionSizeEntity>> = appDatabase.optionSizeDao().getAllData()
    override suspend fun insertAllSizes(sizes: List<OptionSizeEntity>) = appDatabase.optionSizeDao().insertAll(sizes)
    override suspend fun deleteSize(size: OptionSizeEntity) = appDatabase.optionSizeDao().delete(size)
    override suspend fun deleteAllSizes() = appDatabase.optionSizeDao().deleteAllData()
}