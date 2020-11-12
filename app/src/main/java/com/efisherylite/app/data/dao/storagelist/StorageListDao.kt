package com.efisherylite.app.data.dao.storagelist

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
@Dao
interface StorageListDao {
    @Query("SELECT * FROM storagelist")
    fun getAllStorage(): LiveData<List<StorageListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(storage: List<StorageListEntity>)

    @Delete
    fun deleteStorage(storage: StorageListEntity)

    @Query("DELETE FROM storagelist")
    fun deleteAllStorage()
}