package com.efisherylite.app.domain.room.database

import androidx.lifecycle.LiveData
import com.efisherylite.app.data.dao.optionarea.OptionAreaEntity
import com.efisherylite.app.data.dao.optionsize.OptionSizeEntity
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
    suspend fun insertAllStorage(storages: List<StorageListEntity>)
    suspend fun deleteStorage(storage: StorageListEntity)
    suspend fun deleteAllStorage()

    /**
     * Option Area Database
     */
    fun getAllAreas(): LiveData<List<OptionAreaEntity>>
    suspend fun insertAllAreas(areas: List<OptionAreaEntity>)
    suspend fun deleteArea(area: OptionAreaEntity)
    suspend fun deleteAllAreas()

    /**
     * Option Size Database
     */
    fun getAllSizes(): LiveData<List<OptionSizeEntity>>
    suspend fun insertAllSizes(sizes: List<OptionSizeEntity>)
    suspend fun deleteSize(size: OptionSizeEntity)
    suspend fun deleteAllSizes()
}