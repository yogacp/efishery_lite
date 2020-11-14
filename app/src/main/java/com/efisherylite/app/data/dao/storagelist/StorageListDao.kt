package com.efisherylite.app.data.dao.storagelist

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
@Dao
interface StorageListDao {
    @Query("SELECT * FROM storages GROUP BY area_provinsi")
    fun getAllStorage(): LiveData<List<StorageListEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(storage: List<StorageListEntity>)

    @Delete
    suspend fun deleteStorage(storage: StorageListEntity)

    @Query("DELETE FROM storages")
    suspend fun deleteAllStorage()
}